package com.enric.microservices.order_service.order;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;

@Configuration
public class OrderMessagingConfig {

    public static final String ORDER_EXCHANGE = "orders.exchange";
    public static final String ORDER_CREATED_ROUTING_KEY = "order.created";

    @Bean
    //metodo que define donde se publica el msg
    public TopicExchange orderExchange() {
        return new TopicExchange(ORDER_EXCHANGE, true, false);
    }

    @Bean
    //metodo que transforma OrderCreatedEvent en JSON para que se pueda enviar por RabbitMQ
    public MessageConverter jsonMessageConverter() {
        return new JacksonJsonMessageConverter();
    }
}