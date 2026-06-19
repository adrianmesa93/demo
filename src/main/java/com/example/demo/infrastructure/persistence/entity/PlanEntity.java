package com.example.demo.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "plans")
@Getter
@Setter
public class PlanEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(name = "price_cents", nullable = false)
    private Integer priceCents;

    @Column(nullable = false, length = 3)
    private String currency;

    @Column(name = "billing_period", nullable = false)
    private String billingPeriod;

    @Column(nullable = false)
    private boolean active;

    public PlanEntity() {}

    public PlanEntity(UUID id, String name, Integer priceCents, String currency, String billingPeriod, boolean active) {
        this.id = id;
        this.name = name;
        this.priceCents = priceCents;
        this.currency = currency;
        this.billingPeriod = billingPeriod;
        this.active = active;
    }
}
