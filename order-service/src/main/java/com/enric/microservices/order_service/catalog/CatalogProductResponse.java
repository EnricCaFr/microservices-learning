package com.enric.microservices.order_service.catalog;

import java.math.BigDecimal;

public record CatalogProductResponse(
        Long id,
        String name,
        BigDecimal price,
        boolean available
) {
}