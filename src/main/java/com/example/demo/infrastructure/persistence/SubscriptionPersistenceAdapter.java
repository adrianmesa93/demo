package com.example.demo.infrastructure.persistence;

import com.example.demo.domain.model.Subscription;
import com.example.demo.domain.repository.SubscriptionRepository;
import com.example.demo.infrastructure.persistence.entity.SubscriptionEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class SubscriptionPersistenceAdapter implements SubscriptionRepository {

    private final SpringDataSubscriptionRepository repository;

    public SubscriptionPersistenceAdapter(SpringDataSubscriptionRepository repository){
        this.repository = repository;
    }
    @Override
    public Subscription save(Subscription subscription) {
        SubscriptionEntity entity = new SubscriptionEntity(
                subscription.id(), subscription.userId(), subscription.planId(),
                subscription.status(), subscription.startDate(), subscription.endDate()
        );
        SubscriptionEntity saved = repository.save(entity);
        return mapToDomain(saved);
    }

    @Override
    public Optional<Subscription> findByUserId(UUID userId) {
        return repository.findByUserId(userId).map(this::mapToDomain);
    }

    private Subscription mapToDomain(SubscriptionEntity entity) {
        return new Subscription(
                entity.getId(), entity.getUserId(), entity.getPlanId(),
                entity.getStatus(), entity.getStartDate(), entity.getEndDate()
        );
    }
}
