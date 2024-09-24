package com.example.loan_app.mapper;

import com.example.loan_app.dto.request.LoanTransactionRequest;
import com.example.loan_app.dto.response.LoanTransactionResponse;
import com.example.loan_app.entity.Customer;
import com.example.loan_app.entity.InstalmentType;
import com.example.loan_app.entity.LoanTransaction;
import com.example.loan_app.entity.LoanType;

public class LoanTransactionMapper {
    public static LoanTransaction mapToLoanTransaction(LoanType loanType, InstalmentType instalmentType, Customer customer, LoanTransactionRequest request) {
        return LoanTransaction.builder()
                .loanType(loanType)
                .instalmentType(instalmentType)
                .customer(customer)
                .nominal(request.getNominal())
                .build();
    }

    public static LoanTransactionResponse mapToLoanTransactionResponse(LoanTransaction loanTransaction) {
        return LoanTransactionResponse.builder()
                .id(loanTransaction.getId())
                .loanType(loanTransaction.getLoanType().getType())
                .instalmentType(loanTransaction.getInstalmentType().getInstalmentType().toString())
                .customer(loanTransaction.getCustomer().getId())
                .nominal(loanTransaction.getNominal())
                .approvalStatus(loanTransaction.getApprovalStatus())
                .approvedAt(loanTransaction.getApprovedAt())
                .approvedBy(loanTransaction.getApprovedBy())
                .loanTransactionDetails(loanTransaction.getLoanTransactionDetails())
                .createdAt(loanTransaction.getCreatedAt())
                .updatedAt(loanTransaction.getModifiedAt())
                .build();
    }
}
