package com.enric.microservices.notification_service.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OrderCreatedListener {

    private static final Logger log = LoggerFactory.getLogger(OrderCreatedListener.class);

    @RabbitListener(queues = NotificationMessagingConfig.ORDER_CREATED_QUEUE)
    public void handle(OrderCreatedEvent event) {
        log.info("Notification sent for order {}, product {}, quantity {}",event.orderId(),event.productId(),event.quantity());
    }
}