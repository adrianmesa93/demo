package com.example.demo.application.dto;

import java.util.UUID;

public record SubscribeUserCommand(
        UUID userId,
        UUID planId
) {
}
