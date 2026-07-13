package com.example.demo.domain.event;

public interface EventPublisher {
    void publishSubscriptionCreated(SubscriptionCreatedEvent event);
}
