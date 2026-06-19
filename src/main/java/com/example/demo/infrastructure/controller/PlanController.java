package com.example.demo.infrastructure.controller;

import com.example.demo.application.dto.CreatePlanCommand;
import com.example.demo.application.service.CreatePlanUseCase;
import com.example.demo.domain.model.Plan;
import com.example.demo.domain.repository.PlanRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/plans")
public class PlanController {

    private final CreatePlanUseCase createPlanUseCase;
    private final PlanRepository planRepository;

    public PlanController(CreatePlanUseCase createPlanUseCase, PlanRepository planRepository){
        this.createPlanUseCase = createPlanUseCase;
        this.planRepository = planRepository;
    }

    @PostMapping
    public ResponseEntity<Plan> createPlan(@RequestBody CreatePlanCommand command){
        Plan savedPlan = createPlanUseCase.execute(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPlan);
    }

    @GetMapping
    public ResponseEntity<List<Plan>> getAllActivePlans() {
        List<Plan> activePlans = planRepository.findByActiveTrue();
        return ResponseEntity.ok(activePlans);
    }
}
