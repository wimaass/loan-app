package com.example.loan_app.service;

import com.example.loan_app.entity.AppUser;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    AppUser loadUserByUserId(String id);
    UserDetails loadUserByEmail(String email);
}
