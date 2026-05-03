package com.springboot.restfull_web_services.ecommerce;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse(
        Long id,
        String customerName,
        String customerEmail,
        String shippingAddress,
        LocalDateTime createdAt,
        BigDecimal totalAmount,
        String status,
        List<OrderItemResponse> items
) {
    static OrderResponse from(CustomerOrder order) {
        return new OrderResponse(
                order.getId(),
                order.getCustomerName(),
                order.getCustomerEmail(),
                order.getShippingAddress(),
                order.getCreatedAt(),
                order.getTotalAmount(),
                order.getStatus(),
                order.getItems().stream().map(OrderItemResponse::from).toList()
        );
    }
}
