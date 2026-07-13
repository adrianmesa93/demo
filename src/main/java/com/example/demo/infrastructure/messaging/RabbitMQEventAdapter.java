package com.example.demo.infrastructure.messaging;

import com.example.demo.domain.event.EventPublisher;
import com.example.demo.domain.event.SubscriptionCreatedEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQEventAdapter implements EventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public static final String EXCHANGE_NAME = "saas.events";
    public static final String ROUTING_KEY = "subscription.created";

    public RabbitMQEventAdapter(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }
    @Override
    public void publishSubscriptionCreated(SubscriptionCreatedEvent event) {
        System.out.println("===> Publicando evento de suscripción para el usuario: " + event.userId());

        rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, event);
    }
}
