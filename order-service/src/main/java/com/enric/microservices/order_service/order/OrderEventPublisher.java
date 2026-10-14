package com.enric.microservices.order_service.order;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public OrderEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishOrderCreated(OrderCreatedEvent event) {
        rabbitTemplate.convertAndSend(
                OrderMessagingConfig.ORDER_EXCHANGE,
                OrderMessagingConfig.ORDER_CREATED_ROUTING_KEY,
                event
        );
    }
}