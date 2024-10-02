package com.example.loan_app.entity;

import com.example.loan_app.constant.PathDb;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = PathDb.LOAN_TRANSACTION_DETAIL)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanTransactionDetail extends AuditEntity{
    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;

    private Double nominal;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "loan_transaction_id")
    private LoanTransaction loanTransaction;

    @Enumerated(EnumType.STRING)
    private LoanStatus loanStatus;
}
