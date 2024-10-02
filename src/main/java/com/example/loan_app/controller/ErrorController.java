package com.example.loan_app.controller;

import com.example.loan_app.dto.response.CommonResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import static com.example.loan_app.mapper.CommonResponseMapper.getCommonResponse;

@RestControllerAdvice
public class ErrorController {
    private static String message;
    private static HttpStatus status;

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> responseStatusException(ResponseStatusException e) {
        message = e.getReason();
        CommonResponse<?> response = getCommonResponse(message);

        return ResponseEntity
                .status(e.getStatusCode())
                .body(response);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> constraintViolationException(ConstraintViolationException e) {
        message = e.getMessage();
        status = HttpStatus.BAD_REQUEST;

        CommonResponse<?> response = getCommonResponse(message, status);

        return ResponseEntity
                .status(status)
                .body(response);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<?> usernameNotFoundException(UsernameNotFoundException e) {
        message = e.getMessage();
        status = HttpStatus.UNAUTHORIZED;
        CommonResponse<?> response = getCommonResponse(message, status);

        return ResponseEntity
                .status(status)
                .body(response);
    }
}
