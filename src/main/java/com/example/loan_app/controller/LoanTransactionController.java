package com.example.loan_app.controller;

import com.example.loan_app.constant.Message;
import com.example.loan_app.constant.PathApi;
import com.example.loan_app.dto.request.LoanTransactionRequest;
import com.example.loan_app.dto.response.CommonResponse;
import com.example.loan_app.dto.response.LoanTransactionResponse;
import com.example.loan_app.service.LoanTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.loan_app.mapper.CommonResponseMapper.getCommonResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping(PathApi.TRANSACTION_API)
public class LoanTransactionController {
    private final LoanTransactionService loanTransactionService;
    public static String message;
    public static HttpStatus status;

    @PostMapping
    public ResponseEntity<?> createLoanTransaction(@RequestBody LoanTransactionRequest request){
        LoanTransactionResponse loanTransactionResponse = loanTransactionService.createLoanTransaction(request);
        message = Message.CREATE_SUCCESS + " Transaction";
        status = HttpStatus.CREATED;

        CommonResponse<?> response = getCommonResponse(message, status, loanTransactionResponse);

        return ResponseEntity
                .status(status)
                .body(response);
    }
}
