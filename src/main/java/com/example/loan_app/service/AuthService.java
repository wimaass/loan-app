package com.example.loan_app.service;

import com.example.loan_app.dto.request.AuthRequest;
import com.example.loan_app.dto.response.RegisterResponse;

public interface AuthService {
    RegisterResponse register(AuthRequest authRequest);
}
