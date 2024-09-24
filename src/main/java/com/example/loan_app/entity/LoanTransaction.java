package com.example.loan_app.entity;

import com.example.loan_app.constant.PathDb;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = PathDb.LOAN_TRANSACTION)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanTransaction extends AuditEntity{
    @ManyToOne
    @JoinColumn(name = "loan_type_id")
    private LoanType loanType;

    @ManyToOne
    @JoinColumn(name = "instalment_type_id")
    private InstalmentType instalmentType;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private Double nominal;

    @Column(name = "approved_at")
    private LocalDateTime approvedAt;

    @Column(name = "approved_by")
    private String approvedBy;

    @Column(name = "approval_status")
    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus;

    @OneToMany(mappedBy = "loanTransaction", cascade = {CascadeType.MERGE, CascadeType.MERGE})
    private List<LoanTransactionDetail> loanTransactionDetails;
}
