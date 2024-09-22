package com.example.loan_app.service.impl;

import com.example.loan_app.entity.AppUser;
import com.example.loan_app.entity.ERole;
import com.example.loan_app.entity.Role;
import com.example.loan_app.entity.User;
import com.example.loan_app.repository.UserRepository;
import com.example.loan_app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public AppUser loadUserByUserId(String id) {
        User user = userRepository.findById(id).
                orElseThrow(() -> new UsernameNotFoundException("Invalid Credentials"));
        List<ERole> roles = new ArrayList<>();
        for (Role role : user.getRoles()) {
            roles.add(role.getRole());
        }
        return AppUser.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .roles(roles)
                .build();

    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException("Invalid credential"));

        List<ERole> roles = new ArrayList<>();
        for (Role role : user.getRoles()) {
            roles.add(role.getRole());
        }

        return AppUser.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .roles(roles)
                .build();
    }
}
