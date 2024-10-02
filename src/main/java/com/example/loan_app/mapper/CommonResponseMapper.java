package com.example.loan_app.mapper;

import com.example.loan_app.dto.response.CommonResponse;
import org.springframework.http.HttpStatus;

public class CommonResponseMapper {
    public static <T> CommonResponse<T> getCommonResponse(String message ,HttpStatus statusCode, T data) {
        return CommonResponse.<T>builder()
                .message(message)
                .data(data)
                .statusCode(statusCode)
                .build();
    }

    public static <T> CommonResponse<T> getCommonResponse(String message ,HttpStatus statusCode) {
        return CommonResponse.<T>builder()
                .message(message)
                .statusCode(statusCode)
                .build();
    }

    public static <T> CommonResponse<T> getCommonResponse(String message) {
        return CommonResponse.<T>builder()
                .message(message)
                .build();
    }
}
