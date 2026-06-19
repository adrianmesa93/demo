package com.example.demo.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public record Subscription(
        UUID id,
        UUID userId,
        UUID planId,
        String status,
        LocalDateTime startDate,
        LocalDateTime endDate
) {
    public boolean isActive() {
        return "ACTIVE".equals(status) && LocalDateTime.now().isBefore(endDate);
    }

    public Subscription renew(int months){
        return new Subscription(
                this.id,
                this.userId,
                this.planId,
                "ACTIVE",
                this.startDate,
                this.endDate.plusMonths(months)
        );
    }
}
