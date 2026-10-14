package com.enric.microservices.order_service.order;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse create(@Valid @RequestBody CreateOrderRequest request) {
        Order order = orderService.create(
                request.productId(),
                request.quantity()
        );

        return OrderResponse.from(order);
    }

    @GetMapping
    public List<OrderResponse> findAll() {
        List<OrderResponse> responses = new ArrayList<>();

        for (Order order : orderService.findAll()) {
            responses.add(OrderResponse.from(order));
        }

        return responses;
    }

    @GetMapping("/{id}")
    public OrderResponse findById(@PathVariable Long id) {
        Order order = orderService.findById(id);
        return OrderResponse.from(order);
    }
}