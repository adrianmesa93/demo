package com.example.demo.application.dto;

public record LoginCommand(
        String email,
        String password
) {
}
