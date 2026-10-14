package com.enric.microservices.notification_service.notification;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificationMessagingConfig {

    public static final String ORDER_EXCHANGE = "orders.exchange";
    public static final String ORDER_CREATED_QUEUE = "notification.order-created.queue";
    public static final String ORDER_CREATED_ROUTING_KEY = "order.created";

    @Bean
    //delcara un exchange de tipo topic, que es el que permite hacer routing de mensajes a colas
    public TopicExchange orderExchange() {
        return new TopicExchange(ORDER_EXCHANGE, true, false);
    }

    @Bean
    //almacena los mensajes en una cola, que es donde se van a consumir los mensajes
    public Queue orderCreatedQueue() {
        return QueueBuilder.durable(ORDER_CREATED_QUEUE).build();
    }

    @Bean
    //hace el binding entre la cola y el exchange, para que los mensajes que lleguen al exchange se envien a la cola
    public Binding orderCreatedBinding(Queue orderCreatedQueue,TopicExchange orderExchange) {
        return BindingBuilder
                .bind(orderCreatedQueue)
                .to(orderExchange)
                .with(ORDER_CREATED_ROUTING_KEY);
    }

    @Bean
    //convierte el json recibido en un java object
    public MessageConverter jsonMessageConverter() {
        return new JacksonJsonMessageConverter();
    }
}