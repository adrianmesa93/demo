package com.example.demo.domain.model;

import java.io.Serializable;
import java.util.UUID;

public record Plan (
    UUID id,
    String name,
    Integer priceCents,
    String currency,
    String billingPeriod,
    boolean active
) implements Serializable {

    public Plan{
        if (priceCents < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
    }
}
