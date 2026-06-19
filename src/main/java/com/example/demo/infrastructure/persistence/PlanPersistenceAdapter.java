package com.example.demo.infrastructure.persistence;

import com.example.demo.domain.model.Plan;
import com.example.demo.domain.repository.PlanRepository;
import com.example.demo.infrastructure.persistence.entity.PlanEntity;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class PlanPersistenceAdapter implements PlanRepository {

    private final SpringDataPlanRepository springDataPlanRepository;

    public PlanPersistenceAdapter(SpringDataPlanRepository springDataPlanRepository){
        this.springDataPlanRepository = springDataPlanRepository;
    }

    @Override
    @CacheEvict(value = "active_plans", allEntries = true)
    public Plan save(Plan plan) {
        PlanEntity entity = new PlanEntity(
                plan.id(), plan.name(), plan.priceCents(),
                plan.currency(), plan.billingPeriod(), plan.active()
        );
        PlanEntity saved = springDataPlanRepository.save(entity);
        return  mapToDomain(saved);
    }

    @Override
    public Optional<Plan> findById(UUID id) {
        return springDataPlanRepository.findById(id).map(this::mapToDomain);
    }

    @Override
    @Cacheable(value = "active_plans")
    public List<Plan> findByActiveTrue() {
        System.out.println("=== ¡HOLA! Estoy yendo a buscar a la Base de Datos PostgreSQL ===");
        return springDataPlanRepository.findByActiveTrue().stream()
                .map(this::mapToDomain)
                .collect(Collectors.toList());
    }

    private Plan mapToDomain(PlanEntity entity) {
        return new Plan(
                entity.getId(), entity.getName(), entity.getPriceCents(),
                entity.getCurrency(), entity.getBillingPeriod(), entity.isActive()
        );
    }
}
