package com.example.demo.application.dto;

public record CreatePlanCommand(
        String name,
        Integer priceCents,
        String currency,
        String billingPeriod
) {
}
