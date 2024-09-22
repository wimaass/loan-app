package com.example.loan_app.controller;

import com.example.loan_app.constant.PathApi;
import com.example.loan_app.dto.request.AuthRequest;
import com.example.loan_app.dto.response.CommonResponse;
import com.example.loan_app.dto.response.LoginResponse;
import com.example.loan_app.dto.response.RegisterResponse;
import com.example.loan_app.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.loan_app.mapper.CommonResponseMapper.getCommonResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping(PathApi.AUTHENTICATE_API)
@Slf4j
public class AuthController {
    private final AuthService authService;
    private static String message;
    private static HttpStatus statusCode;


    @PostMapping(PathApi.SIGN_UP_API)
    ResponseEntity<?> signup (@RequestBody AuthRequest authRequest) {
        RegisterResponse registerResponse = authService.register(authRequest);
        message = "Successfully registered Admin!";
        statusCode = HttpStatus.CREATED;

        CommonResponse<?> response = getCommonResponse(message, statusCode, registerResponse);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping(PathApi.SIGN_IN)
    ResponseEntity<?> signIn (@RequestBody AuthRequest authRequest) {
        LoginResponse loginResponse = authService.login(authRequest);
        log.info("SignIn request: {}", loginResponse);
        message = "Successfully logged in!";
        statusCode = HttpStatus.OK;

        CommonResponse<?> response = getCommonResponse(message,statusCode,loginResponse);
        return ResponseEntity
                .status(statusCode)
                .body(response);
    }
}
