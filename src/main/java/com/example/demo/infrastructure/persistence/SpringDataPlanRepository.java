package com.example.demo.infrastructure.persistence;

import com.example.demo.infrastructure.persistence.entity.PlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SpringDataPlanRepository extends JpaRepository<PlanEntity, UUID> {
    List<PlanEntity> findByActiveTrue();
}
