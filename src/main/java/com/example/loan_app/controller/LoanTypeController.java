package com.example.loan_app.controller;

import com.example.loan_app.constant.Message;
import com.example.loan_app.constant.PathApi;
import com.example.loan_app.dto.request.LoanTypeRequest;
import com.example.loan_app.dto.response.CommonResponse;
import com.example.loan_app.entity.LoanType;
import com.example.loan_app.service.LoanTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.loan_app.mapper.CommonResponseMapper.getCommonResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping(PathApi.LOAN_TYPE_API)
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_STAFF')")
public class LoanTypeController {
    private final LoanTypeService loanTypeService;
    private static String message;
    private static HttpStatus status;

    @PostMapping
    public ResponseEntity<?> createLoanType(@RequestBody LoanTypeRequest request) {
        LoanType loanType = loanTypeService.createLoanType(request);
        message = Message.CREATE_SUCCESS + " loan-type";
        status = HttpStatus.CREATED;

        CommonResponse<?> response = getCommonResponse(message, status, loanType);

        return ResponseEntity
                .status(status)
                .body(response);
    }

    @GetMapping(PathApi.GET_ID)
    public ResponseEntity<?> getLoanTypeById(@PathVariable String id) {
        LoanType loanType = loanTypeService.getLoanTypeById(id);
        message = Message.GET_BY_ID_SUCCESS;
        status = HttpStatus.OK;

        CommonResponse<?> response = getCommonResponse(message, status, loanType);

        return ResponseEntity
                .status(status)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<?> getAllLoanTypes() {
        List<LoanType> loanTypes = loanTypeService.getAllLoanTypes();
        message = Message.GET_ALL_SUCCESS;
        status = HttpStatus.OK;

        CommonResponse<?> response = getCommonResponse(message, status, loanTypes);

        return ResponseEntity
                .status(status)
                .body(response);
    }

    @PutMapping
    public ResponseEntity<?> updateLoanType(@RequestBody LoanTypeRequest request) {
        LoanType loanType = loanTypeService.updateLoanType(request);
        message = Message.UPDATE_SUCCESS;
        status = HttpStatus.OK;

        CommonResponse<?> response = getCommonResponse(message, status, loanType);

        return ResponseEntity
                .status(status)
                .body(response);
    }

    @DeleteMapping(PathApi.GET_ID)
    public ResponseEntity<?> deleteLoanType(@PathVariable String id) {
        loanTypeService.deleteLoanType(id);
        message = Message.DELETE_SUCCESS;
        status = HttpStatus.OK;

        CommonResponse<?> response = getCommonResponse(message, status);

        return ResponseEntity
                .status(status)
                .body(response);
    }
}
