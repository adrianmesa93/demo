package com.example.demo.application.service;

import com.example.demo.application.dto.SubscribeUserCommand;
import com.example.demo.domain.event.EventPublisher;
import com.example.demo.domain.event.SubscriptionCreatedEvent;
import com.example.demo.domain.model.Plan;
import com.example.demo.domain.model.Subscription;
import com.example.demo.domain.repository.PlanRepository;
import com.example.demo.domain.repository.SubscriptionRepository;
import com.example.demo.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class SubscribeUserUseCase {

    private final UserRepository userRepository;
    private final PlanRepository planRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final EventPublisher eventPublisher;

    public SubscribeUserUseCase(UserRepository userRepository, PlanRepository planRepository, SubscriptionRepository subscriptionRepository, EventPublisher eventPublisher){
        this.userRepository = userRepository;
        this.planRepository = planRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.eventPublisher = eventPublisher;
    }

    public Subscription execute(SubscribeUserCommand command){
        userRepository.findById(command.userId())
                .orElseThrow(() -> new IllegalArgumentException("El usuario no existe"));

        Plan plan = planRepository.findById(command.planId())
                .orElseThrow(() -> new IllegalArgumentException("El plan no existe"));

        if (!plan.active()) {
            throw new IllegalArgumentException("El plan seleccionado no está activo");
        }

        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = "ANNUAL".equalsIgnoreCase(plan.billingPeriod())
                ? startDate.plusYears(1)
                : startDate.plusMonths(1);

        Subscription newSubscription = new Subscription(
                UUID.randomUUID(),
                command.userId(),
                command.planId(),
                "ACTIVE",
                startDate,
                endDate
        );

        Subscription savedSubscription = subscriptionRepository.save(newSubscription);

        SubscriptionCreatedEvent event = new SubscriptionCreatedEvent(
                savedSubscription.id(),
                savedSubscription.userId(),
                savedSubscription.planId()
        );
        eventPublisher.publishSubscriptionCreated(event);

        return savedSubscription;
    }
}
