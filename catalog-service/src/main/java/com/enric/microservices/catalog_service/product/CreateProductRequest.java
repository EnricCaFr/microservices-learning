package com.enric.microservices.catalog_service.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record CreateProductRequest(

        @NotBlank
        @Size(max = 150)
        String name,

        @NotNull
        @PositiveOrZero
        BigDecimal price,

        @NotNull
        Boolean available

) {
}