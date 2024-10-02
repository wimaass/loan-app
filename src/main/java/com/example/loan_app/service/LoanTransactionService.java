package com.example.loan_app.service;

import com.example.loan_app.dto.request.ApprovalRequest;
import com.example.loan_app.dto.request.LoanTransactionRequest;
import com.example.loan_app.dto.response.LoanTransactionResponse;

public interface LoanTransactionService {
    LoanTransactionResponse createLoanTransaction(LoanTransactionRequest loanTransactionRequest);
    LoanTransactionResponse getLoanTransactionById(String id);
    LoanTransactionResponse approveLoanTransactionByAdmin(ApprovalRequest request);
    LoanTransactionResponse payLoanTransaction(String id);
}
