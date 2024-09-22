package com.example.loan_app.mapper;

import com.example.loan_app.dto.request.LoanTypeRequest;
import com.example.loan_app.entity.LoanType;

public class LoanTypeMapper {
    public static LoanType mapToLoanType(LoanTypeRequest request) {
        return LoanType.builder()
                .type(request.getType())
                .maxLoan(request.getMaxLoan())
                .build();
    }
}
