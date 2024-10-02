package com.example.loan_app.service.impl;

import com.example.loan_app.dto.request.AuthRequest;
import com.example.loan_app.dto.request.CustomerRequest;
import com.example.loan_app.dto.response.LoginResponse;
import com.example.loan_app.dto.response.RegisterResponse;
import com.example.loan_app.entity.*;
import com.example.loan_app.repository.UserRepository;
import com.example.loan_app.security.JwtUtil;
import com.example.loan_app.service.AuthService;
import com.example.loan_app.service.CustomerService;
import com.example.loan_app.service.RoleService;
import com.example.loan_app.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.example.loan_app.mapper.CustomerMapper.mapToCustomerRequest;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final RoleService roleService;
    private final ValidationUtil validationUtil;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public RegisterResponse register(AuthRequest authRequest) {
        try {
            validationUtil.validate(authRequest);
            List<Role> roles = List.of(
                    roleService.getOrSaveRole(Role.builder().role(ERole.ROLE_ADMIN).build()),
                    roleService.getOrSaveRole(Role.builder().role(ERole.ROLE_STAFF).build())
            );
            User user = User.builder()
                    .email(authRequest.getEmail())
                    .password(passwordEncoder.encode(authRequest.getPassword()))
                    .roles(roles)
                    .build();
            userRepository.saveAndFlush(user);

            List<String> stringRoles = new ArrayList<>();
            for (Role role : user.getRoles()) {
                stringRoles.add(role.getRole().toString().split("_")[1].toLowerCase());
            }

            return RegisterResponse.builder()
                    .email(user.getEmail())
                    .roles(stringRoles)
                    .build();
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists");
        }
    }

    @Override
    public LoginResponse login(AuthRequest request) {
        validationUtil.validate(request);
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        AppUser appUser = (AppUser) authentication.getPrincipal();
        String token = jwtUtil.generateToken(appUser);

        List<String> roles = new ArrayList<>();
        for (GrantedAuthority role : appUser.getAuthorities()) {
            roles.add(role.toString().split("_")[1].toLowerCase());
        }

        return LoginResponse.builder()
                .token(token)
                .roles(appUser.getRoles())
                .build();

    }
}
