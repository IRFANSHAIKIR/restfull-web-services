package com.springboot.restfull_web_services.ecommerce;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;

    public OrderService(OrderRepository orderRepository, ProductService productService) {
        this.orderRepository = orderRepository;
        this.productService = productService;
    }

    @Transactional(readOnly = true)
    public List<OrderResponse> findAllOrders() {
        return orderRepository.findAll().stream().map(OrderResponse::from).toList();
    }

    @Transactional(readOnly = true)
    public OrderResponse findOrder(Long id) {
        return OrderResponse.from(findOrderEntity(id));
    }

    public OrderResponse createOrder(CreateOrderRequest request) {
        CustomerOrder order = new CustomerOrder();
        order.setCustomerName(request.customerName());
        order.setCustomerEmail(request.customerEmail());
        order.setShippingAddress(request.shippingAddress());
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus("PLACED");

        BigDecimal totalAmount = BigDecimal.ZERO;

        for (OrderItemRequest itemRequest : request.items()) {
            Product product = productService.findProductEntity(itemRequest.productId());
            int requestedQuantity = itemRequest.quantity();
            int availableQuantity = product.getStockQuantity() == null ? 0 : product.getStockQuantity();

            if (availableQuantity < requestedQuantity) {
                throw new OutOfStockException("Not enough stock for product: " + product.getName());
            }

            BigDecimal lineTotal = product.getPrice().multiply(BigDecimal.valueOf(requestedQuantity));
            totalAmount = totalAmount.add(lineTotal);

            OrderItem item = new OrderItem();
            item.setProduct(product);
            item.setProductName(product.getName());
            item.setUnitPrice(product.getPrice());
            item.setQuantity(requestedQuantity);
            item.setLineTotal(lineTotal);
            order.addItem(item);

            product.setStockQuantity(availableQuantity - requestedQuantity);
        }

        order.setTotalAmount(totalAmount);
        return OrderResponse.from(orderRepository.save(order));
    }

    private CustomerOrder findOrderEntity(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found: " + id));
    }
}
