package com.example.loan_app.controller;

import com.example.loan_app.constant.Message;
import com.example.loan_app.constant.PathApi;
import com.example.loan_app.dto.request.InstalmentTypeRequest;
import com.example.loan_app.dto.response.CommonResponse;
import com.example.loan_app.entity.InstalmentType;
import com.example.loan_app.service.InstalmentTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.loan_app.mapper.CommonResponseMapper.getCommonResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping(PathApi.INSTALMENT_TYPE_API)
public class InstalmentTypeController {
    private final InstalmentTypeService instalmentTypeService;
    private static String message;
    private static HttpStatus statusCode;

    @PostMapping
    ResponseEntity<?> createInstalmentType(@RequestBody InstalmentTypeRequest request) {
        InstalmentType instalmentType = instalmentTypeService.createInstalmentType(request);
        message = Message.CREATE_SUCCESS + " instalmentType";
        statusCode = HttpStatus.CREATED;

        CommonResponse<?> response = getCommonResponse(message, statusCode, instalmentType);

        return ResponseEntity
                .status(statusCode)
                .body(response);
    }

    @GetMapping(PathApi.GET_ID)
    ResponseEntity<?> getInstalmentTypeById(@PathVariable String id) {
        InstalmentType instalmentType = instalmentTypeService.getInstalmentTypeById(id);
        message = Message.GET_BY_ID_SUCCESS;
        statusCode = HttpStatus.OK;

        CommonResponse<?> response = getCommonResponse(message, statusCode, instalmentType);

        return ResponseEntity
                .status(statusCode)
                .body(response);
    }
}
