package com.enric.microservices.order_service.order;

public class ProductUnavailableException extends RuntimeException {

    public ProductUnavailableException(Long productId) {
        super("Product is not available with id: " + productId);
    }
}