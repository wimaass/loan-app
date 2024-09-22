package com.example.loan_app.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.loan_app.entity.AppUser;
import com.example.loan_app.entity.ERole;
import com.example.loan_app.entity.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JwtUtil {
    @Value("${app.loan-app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.loan-app.app-name}")
    private String appName;

    @Value("${app.loan-app.jwt-expiration}")
    private long jwtExpirationInSeconds;

    public String generateToken(AppUser appUser){
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes());
            List<String> roles = new ArrayList<>();
            for(GrantedAuthority role : appUser.getAuthorities()){
                roles.add(role.getAuthority());
            }
            String token = JWT.create()
                    .withIssuer(appName)
                    .withSubject(appUser.getId())
                    .withExpiresAt(Instant.now().plusSeconds(jwtExpirationInSeconds))
                    .withIssuedAt(Instant.now())
                    .withClaim("role", roles)
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            log.error("Error generating token", exception.getMessage());
            throw new RuntimeException(exception.getMessage());
        }
    }

    public Boolean verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes());
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT.getIssuer().equals(appName);
        } catch (JWTCreationException exception) {
            log.error("Invalid verification JWT : ", exception.getMessage());
            return false;
        }
    }

    public Map<String, String> getUserInfoByToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes());
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);

            Map<String, String> userInfo = new HashMap<>();

            userInfo.put("userId", decodedJWT.getSubject());
            userInfo.put("role", decodedJWT.getClaim("role").asString());

            return userInfo;
        } catch (JWTCreationException exception) {
            log.error("Invalid verification JWT", exception.getMessage());
            return null;
        }
    }
}
