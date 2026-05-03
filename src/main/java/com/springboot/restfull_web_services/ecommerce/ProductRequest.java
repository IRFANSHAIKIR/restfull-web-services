package com.springboot.restfull_web_services.ecommerce;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ProductRequest(
        @NotBlank(message = "Product name is required")
        String name,

        @Size(max = 1000, message = "Description must be 1000 characters or fewer")
        String description,

        @NotNull(message = "Price is required")
        @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
        BigDecimal price,

        @Min(value = 0, message = "Stock quantity cannot be negative")
        Integer stockQuantity,

        String imageUrl,

        Long categoryId
) {
}
