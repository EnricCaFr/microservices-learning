package com.enric.microservices.order_service.order;

import java.time.Instant;
import java.util.UUID;

//es el contrato del evento que se va a enviar al bus de eventos, es un record que contiene los datos del evento
public record OrderCreatedEvent(
        UUID eventId,
        Long orderId,
        Long productId,
        int quantity,
        Instant createdAt
) {

    //meotod para mapear un objeto Order a un objeto OrderCreatedEvent
    public static OrderCreatedEvent from(Order order) {
        return new OrderCreatedEvent(
                UUID.randomUUID(),
                order.getId(),
                order.getProductId(),
                order.getQuantity(),
                order.getCreatedAt()
        );
    }

}
