package com.example.demo.application.dto;

public record RegisterUserCommand(
        String email,
        String password
) {
}
