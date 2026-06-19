package com.example.demo.application.dto;

public record LoginResponse(
        String token,
        String email,
        String role
) {
}
