package com.example.demo.domain.event;

import java.io.Serializable;
import java.util.UUID;

public record SubscriptionCreatedEvent (
        UUID subscriptionId,
        UUID userId,
        UUID planId
) implements Serializable {}
