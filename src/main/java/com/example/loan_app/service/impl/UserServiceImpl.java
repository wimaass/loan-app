package com.example.loan_app.service.impl;

import com.example.loan_app.entity.AppUser;
import com.example.loan_app.entity.User;
import com.example.loan_app.repository.UserRepository;
import com.example.loan_app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public AppUser loadUserByUserId(String id) {
        User user = userRepository.findById(id).
                orElseThrow(() -> new UsernameNotFoundException("Invalid Credentials"));
        return AppUser.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRoles())
                .build();

    }

    @Override
    public UserDetails loadUserByEmail(String email) {
        User userCredential = userRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException("Invalid credential"));

        return AppUser.builder()
                .id(userCredential.getId())
                .email(userCredential.getEmail())
                .password(userCredential.getPassword())
                .roles(userCredential.getRoles())
                .build();
    }
}
