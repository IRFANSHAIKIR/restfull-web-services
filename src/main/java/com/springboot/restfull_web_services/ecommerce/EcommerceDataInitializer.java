package com.springboot.restfull_web_services.ecommerce;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class EcommerceDataInitializer implements CommandLineRunner {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public EcommerceDataInitializer(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) {
        if (productRepository.count() > 0) {
            return;
        }

        Category electronics = categoryRepository.save(new Category(null, "Electronics"));
        Category fashion = categoryRepository.save(new Category(null, "Fashion"));

        productRepository.save(new Product(
                null,
                "Wireless Headphones",
                "Comfortable Bluetooth headphones with long battery life.",
                new BigDecimal("2499.00"),
                25,
                "https://images.unsplash.com/photo-1505740420928-5e560c06d30e",
                electronics
        ));

        productRepository.save(new Product(
                null,
                "Smart Watch",
                "Fitness tracking, notifications, and all-day battery.",
                new BigDecimal("5999.00"),
                12,
                "https://images.unsplash.com/photo-1523275335684-37898b6baf30",
                electronics
        ));

        productRepository.save(new Product(
                null,
                "Classic T-Shirt",
                "Soft cotton t-shirt for everyday wear.",
                new BigDecimal("799.00"),
                40,
                "https://images.unsplash.com/photo-1521572163474-6864f9cf17ab",
                fashion
        ));
    }
}
