package com.example.loan_app.controller;

import com.example.loan_app.constant.Message;
import com.example.loan_app.constant.PathApi;
import com.example.loan_app.dto.request.ApprovalRequest;
import com.example.loan_app.dto.request.LoanTransactionRequest;
import com.example.loan_app.dto.response.CommonResponse;
import com.example.loan_app.dto.response.LoanTransactionResponse;
import com.example.loan_app.service.LoanTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.example.loan_app.mapper.CommonResponseMapper.getCommonResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping(PathApi.TRANSACTION_API)
public class LoanTransactionController {
    private final LoanTransactionService loanTransactionService;
    public static String message;
    public static HttpStatus status;

    @PostMapping
    public ResponseEntity<?> createLoanTransaction(@RequestBody LoanTransactionRequest request) {
        LoanTransactionResponse loanTransactionResponse = loanTransactionService.createLoanTransaction(request);
        message = Message.CREATE_SUCCESS + " Transaction";
        status = HttpStatus.CREATED;

        CommonResponse<?> response = getCommonResponse(message, status, loanTransactionResponse);

        return ResponseEntity
                .status(status)
                .body(response);
    }

    @GetMapping(PathApi.GET_ID)
    public ResponseEntity<?> getLoanTransactionById(@PathVariable String id) {
        LoanTransactionResponse loanTransactionResponse = loanTransactionService.getLoanTransactionById(id);
        message = Message.GET_BY_ID_SUCCESS;
        status = HttpStatus.OK;

        CommonResponse<?> response = getCommonResponse(message, status, loanTransactionResponse);

        return ResponseEntity
                .status(status)
                .body(response);
    }

    @PostMapping(PathApi.GET_ID+"/approve")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF')")
    public ResponseEntity<?> approveLoanTransaction(
            @PathVariable String id,
            @RequestBody ApprovalRequest request
    ) {
        request.setAdminId(id);
        LoanTransactionResponse loanTransactionResponse = loanTransactionService.approveLoanTransactionByAdmin(request);
        message = Message.APPROVED;
        status = HttpStatus.OK;

        CommonResponse<?> response = getCommonResponse(message, status, loanTransactionResponse);

        return ResponseEntity
                .status(status)
                .body(response);
    }

    @PutMapping(PathApi.GET_ID+"/pay")
    public ResponseEntity<?> payLoanTransaction(@PathVariable String id){
        LoanTransactionResponse loanTransactionResponse = loanTransactionService.payLoanTransaction(id);
        message = Message.SUCCESS_PAYMENT;
        status = HttpStatus.OK;

        CommonResponse<?> response = getCommonResponse(message, status, loanTransactionResponse);

        return ResponseEntity
                .status(status)
                .body(response);
    }
}
