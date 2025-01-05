package com.example.loan_app.repository;

import com.example.loan_app.entity.LoanStatus;
import com.example.loan_app.entity.LoanTransactionDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanTransactionDetailRepository extends JpaRepository<LoanTransactionDetail, String> {
//    @Query(value = "SELECT * FROM trx_loan_detail WHERE loan_status = :loanStatus AND loan_transaction_id = :loanTransactionId", nativeQuery = true)
//    List<LoanTransactionDetail> findByLoanStatusAndLoanTransactionId(@Param("loanStatus") LoanStatus loanStatus, @Param("loanTransactionId") String loanTransactionId);
@Query(value = "SELECT * FROM trx_loan_detail WHERE loan_status = :loanStatus AND loan_transaction_id = :loanTransactionId", nativeQuery = true)
List<LoanTransactionDetail> findByLoanStatusAndLoanTransactionId(@Param("loanStatus") String loanStatus, @Param("loanTransactionId") String loanTransactionId);
}

