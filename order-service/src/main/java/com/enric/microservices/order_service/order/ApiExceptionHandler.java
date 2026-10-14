package com.enric.microservices.order_service.order;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.enric.microservices.order_service.catalog.CatalogProductNotFoundException;
import com.enric.microservices.order_service.catalog.CatalogUnavailableException;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(OrderNotFoundException.class)
    public ProblemDetail handleOrderNotFound(OrderNotFoundException exception) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                exception.getMessage()
        );

        problem.setTitle("Order not found");

        return problem;
    }

    @ExceptionHandler(CatalogProductNotFoundException.class)
    public ProblemDetail handleCatalogProductNotFound( CatalogProductNotFoundException exception) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                exception.getMessage()
        );

        problem.setTitle("Invalid product");

        return problem;
    }

    @ExceptionHandler(ProductUnavailableException.class)
    public ProblemDetail handleProductUnavailable(ProductUnavailableException exception) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.CONFLICT,
                exception.getMessage()
        );

        problem.setTitle("Product unavailable");

        return problem;
    }

    @ExceptionHandler(CatalogUnavailableException.class)
    public ProblemDetail handleCatalogUnavailable(CatalogUnavailableException exception) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.SERVICE_UNAVAILABLE,
                exception.getMessage()
        );

        problem.setTitle("Catalog unavailable");

        return problem;
    }

}