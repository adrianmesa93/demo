package com.example.demo.infrastructure.controller;

import com.example.demo.application.dto.RegisterUserCommand;
import com.example.demo.application.service.RegisterUserUseCase;
import com.example.demo.domain.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final RegisterUserUseCase registerUserUseCase;

    public UserController(RegisterUserUseCase registerUserUseCase){
        this.registerUserUseCase = registerUserUseCase;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> registerUser(@RequestBody RegisterUserCommand command){
        User savedUser = registerUserUseCase.execute(command);

        Map<String, Object> response = Map.of(
                "id", savedUser.id(),
                "email", savedUser.email(),
                "role", savedUser.role(),
                "createdAt", savedUser.createdAt()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
