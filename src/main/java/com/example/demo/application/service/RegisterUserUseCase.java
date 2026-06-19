package com.example.demo.application.service;

import com.example.demo.application.dto.RegisterUserCommand;
import com.example.demo.domain.model.User;
import com.example.demo.domain.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class RegisterUserUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterUserUseCase(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User execute(RegisterUserCommand command){

        if(userRepository.findByEmail(command.email()).isPresent()){
            throw new IllegalArgumentException("El email ya está registrado");
        }

        String hashedPassword = passwordEncoder.encode(command.password());

        User newUser = new User(
                UUID.randomUUID(),
                command.email(),
                hashedPassword,
                "ROLE_USER",
                LocalDateTime.now()
        );

        return userRepository.save(newUser);
    }
}
