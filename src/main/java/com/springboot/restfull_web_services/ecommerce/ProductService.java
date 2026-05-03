package com.springboot.restfull_web_services.ecommerce;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional(readOnly = true)
    public List<ProductResponse> findProducts(Long categoryId) {
        List<Product> products = categoryId == null
                ? productRepository.findAll()
                : productRepository.findByCategoryId(categoryId);
        List<ProductResponse> productList = products.stream().map(ProductResponse::from).toList();
        return products.stream().map(ProductResponse::from).toList();
    }

    @Transactional(readOnly = true)
    public ProductResponse findProduct(Long id) {
        return ProductResponse.from(findProductEntity(id));
    }

    public ProductResponse createProduct(ProductRequest request) {
        Product product = new Product();
        applyRequest(product, request);
        return ProductResponse.from(productRepository.save(product));
    }

    public ProductResponse updateProduct(Long id, ProductRequest request) {
        Product product = findProductEntity(id);
        applyRequest(product, request);
        return ProductResponse.from(product);
    }

    public void deleteProduct(Long id) {
        Product product = findProductEntity(id);
        productRepository.delete(product);
    }

    Product findProductEntity(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found: " + id));
    }

    private void applyRequest(Product product, ProductRequest request) {
        product.setName(request.name());
        product.setDescription(request.description());
        product.setPrice(request.price());
        product.setStockQuantity(request.stockQuantity() == null ? 0 : request.stockQuantity());
        product.setImageUrl(request.imageUrl());
        product.setCategory(findCategory(request.categoryId()));
    }

    private Category findCategory(Long categoryId) {
        if (categoryId == null) {
            return null;
        }

        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found: " + categoryId));
    }
}
