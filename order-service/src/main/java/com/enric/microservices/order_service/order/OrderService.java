package com.enric.microservices.order_service.order;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.enric.microservices.order_service.catalog.CatalogClient;
import com.enric.microservices.order_service.catalog.CatalogProductResponse;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;

    private final CatalogClient catalogClient;

    private final OrderEventPublisher orderEventPublisher;

    public OrderService(OrderRepository orderRepository, CatalogClient catalogClient, OrderEventPublisher orderEventPublisher) {
        this.orderRepository = orderRepository;
        this.catalogClient = catalogClient;
        this.orderEventPublisher = orderEventPublisher;
    }

    @Transactional
    public Order create(Long productId, int quantity) {
        CatalogProductResponse product = catalogClient.findProductById(productId);

        if (!product.available()) {
            throw new ProductUnavailableException(productId);
        }

        Order order = new Order(productId, quantity);
        Order savedOrder = orderRepository.save(order);

        //esto es para publicar el evento de que se ha creado una orden, para que otros microservicios puedan reaccionar a este evento
        OrderCreatedEvent event = OrderCreatedEvent.from(savedOrder);
        orderEventPublisher.publishOrderCreated(event);

        return savedOrder;
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
    }
}