package com.example.demo.infrastructure.controller;

import com.example.demo.application.dto.SubscribeUserCommand;
import com.example.demo.application.service.SubscribeUserUseCase;
import com.example.demo.domain.model.Subscription;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/subscriptions")
public class SubscriptionController {

    private final SubscribeUserUseCase subscribeUserUseCase;

    public SubscriptionController(SubscribeUserUseCase subscribeUserUseCase){
        this.subscribeUserUseCase = subscribeUserUseCase;
    }

    @PostMapping
    public ResponseEntity<Subscription> subscribe(@RequestBody SubscribeUserCommand command){
        Subscription subscription = subscribeUserUseCase.execute(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(subscription);
    }
}
