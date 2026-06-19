package com.example.demo.infrastructure.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    private static final String SECRET_KEY = "MiClaveSecretaSúperSeguraQueNadiePuedeAdivinar123!";
    private static final String ISSUER = "saas-billing-api";
    private static final long EXPIRATION_TIME = 3600000; // 1 hora en milisegundos

    public String generateToken(String email, String role) {
        return JWT.create()
                .withSubject(email)
                .withIssuer(ISSUER)
                .withClaim("role", role) // Metemos el rol dentro del token
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    public DecodedJWT validateToken(String token) {
        return JWT.require(Algorithm.HMAC256(SECRET_KEY))
                .withIssuer(ISSUER)
                .build()
                .verify(token);
    }
}
