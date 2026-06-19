package com.example.demo.application.service;

import com.example.demo.application.dto.CreatePlanCommand;
import com.example.demo.domain.model.Plan;
import com.example.demo.domain.repository.PlanRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreatePlanUseCase {

    private final PlanRepository planRepository;

    public CreatePlanUseCase(PlanRepository planRepository){
        this.planRepository = planRepository;
    }

    public Plan execute(CreatePlanCommand command){
        Plan newPlan = new Plan(
                UUID.randomUUID(),
                command.name(),
                command.priceCents(),
                command.currency() != null ? command.currency() : "EUR",
                command.billingPeriod(),
                true
        );

        return planRepository.save(newPlan);
    }
}
