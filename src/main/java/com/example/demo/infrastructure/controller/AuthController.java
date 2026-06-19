package com.example.demo.infrastructure.controller;

import com.example.demo.application.dto.LoginCommand;
import com.example.demo.application.dto.LoginResponse;
import com.example.demo.application.service.LoginUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final LoginUseCase loginUseCase;

    public AuthController(LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginCommand command) {
        LoginResponse response = loginUseCase.execute(command);
        return ResponseEntity.ok(response);
    }
}
