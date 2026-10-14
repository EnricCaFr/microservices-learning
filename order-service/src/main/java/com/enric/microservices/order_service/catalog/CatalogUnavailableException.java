package com.enric.microservices.order_service.catalog;

public class CatalogUnavailableException extends RuntimeException {

    public CatalogUnavailableException(Throwable cause) {
        super("Catalog service is unavailable", cause);
    }
}