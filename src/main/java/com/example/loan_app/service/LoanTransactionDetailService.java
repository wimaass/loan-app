package com.example.loan_app.service;

import com.example.loan_app.entity.LoanTransactionDetail;

import java.util.List;

public interface LoanTransactionDetailService {
    List<LoanTransactionDetail> getUnpaid(String id);
    LoanTransactionDetail update(LoanTransactionDetail loanTransactionDetail);
}
