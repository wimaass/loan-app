package com.example.loan_app.service;

import com.example.loan_app.dto.request.LoanTransactionRequest;
import com.example.loan_app.dto.response.LoanTransactionResponse;

public interface LoanTransactionService {
    LoanTransactionResponse createLoanTransaction(LoanTransactionRequest loanTransactionRequest);
}
