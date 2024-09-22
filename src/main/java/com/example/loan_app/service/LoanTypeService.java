package com.example.loan_app.service;

import com.example.loan_app.dto.request.LoanTypeRequest;
import com.example.loan_app.entity.LoanType;

import java.util.List;

public interface LoanTypeService {
    LoanType createLoanType(LoanTypeRequest request);
    LoanType getLoanTypeById(String id);
    List<LoanType> getAllLoanTypes();
    LoanType updateLoanType(LoanTypeRequest request);
    void deleteLoanType(String id);
}
