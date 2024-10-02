package com.example.loan_app.repository;

import com.example.loan_app.entity.LoanStatus;
import com.example.loan_app.entity.LoanTransactionDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanTransactionDetailRepository extends JpaRepository<LoanTransactionDetail, String> {
    List<LoanTransactionDetail> findByLoanStatusAndLoanTransactionId(LoanStatus loanStatus, String loanTransactionId);
}

