package com.example.loan_app.controller;

import com.example.loan_app.constant.PathApi;
import com.example.loan_app.dto.request.AuthRequest;
import com.example.loan_app.dto.response.RegisterResponse;
import com.example.loan_app.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(PathApi.AUTHENTICATE_API)
public class AuthController {
    private final AuthService authService;
    private static String message;
    private static HttpStatus statusCode;


    @PostMapping(PathApi.SIGN_UP_API)
    ResponseEntity<?> signup (@RequestBody AuthRequest authRequest) {
        RegisterResponse registerResponse = authService.register(authRequest);
        message = "Successfully registered Admin!";
        statusCode = HttpStatus.CREATED;
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(registerResponse);
    }
}
