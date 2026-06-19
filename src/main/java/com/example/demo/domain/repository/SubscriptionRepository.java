package com.example.demo.domain.repository;

import com.example.demo.domain.model.Subscription;

import java.util.Optional;
import java.util.UUID;

public interface SubscriptionRepository {
    Subscription save(Subscription subscription);
    Optional<Subscription> findByUserId(UUID userId);
}
