package com.springboot.restfull_web_services.ecommerce;

import java.math.BigDecimal;

public record ProductResponse(
        Long id,
        String name,
        String description,
        BigDecimal price,
        Integer stockQuantity,
        String imageUrl,
        Long categoryId,
        String categoryName
) {
    static ProductResponse from(Product product) {
        Category category = product.getCategory();
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStockQuantity(),
                product.getImageUrl(),
                category == null ? null : category.getId(),
                category == null ? null : category.getName()
        );
    }
}
