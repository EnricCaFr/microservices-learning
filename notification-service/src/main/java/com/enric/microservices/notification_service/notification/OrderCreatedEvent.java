package com.enric.microservices.notification_service.notification;

import java.time.Instant;
import java.util.UUID;

public record OrderCreatedEvent(
        UUID eventId,
        Long orderId,
        Long productId,
        int quantity,
        Instant createdAt
) {
}