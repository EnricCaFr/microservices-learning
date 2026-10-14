package com.enric.microservices.order_service.catalog;

public class CatalogProductNotFoundException extends RuntimeException {

    public CatalogProductNotFoundException(Long productId) {
        super("Product does not exist in catalog with id: " + productId);
    }
}