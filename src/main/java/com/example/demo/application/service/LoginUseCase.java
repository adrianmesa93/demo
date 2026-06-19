package com.example.demo.application.service;

import com.example.demo.application.dto.LoginCommand;
import com.example.demo.application.dto.LoginResponse;
import com.example.demo.domain.model.User;
import com.example.demo.domain.repository.UserRepository;
import com.example.demo.infrastructure.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public LoginUseCase(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public LoginResponse execute(LoginCommand command) {
        // 1. Buscar al usuario por email
        User user = userRepository.findByEmail(command.email())
                .orElseThrow(() -> new IllegalArgumentException("Credenciales inválidas"));

        // 2. Comprobar si la contraseña coincide con el hash en BD
        if (!passwordEncoder.matches(command.password(), user.password())) {
            throw new IllegalArgumentException("Credenciales inválidas");
        }

        // 3. Generar el token JWT
        String token = jwtService.generateToken(user.email(), user.role());

        // 4. Devolver la respuesta
        return new LoginResponse(token, user.email(), user.role());
    }
}
