package com.example.demo.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public record User(
        UUID id,
        String email,
        String password,
        String role,
        LocalDateTime createdAt
) {
    public User{
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Email inválido");
        }
    }
}
