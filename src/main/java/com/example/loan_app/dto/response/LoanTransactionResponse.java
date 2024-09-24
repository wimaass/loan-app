package com.example.loan_app.dto.response;

import com.example.loan_app.entity.ApprovalStatus;
import com.example.loan_app.entity.LoanTransactionDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanTransactionResponse {
    private String id;
    private String loanType;
    private String instalmentType;
    private String customer;
    private Double nominal;
    private LocalDateTime approvedAt;
    private String approvedBy;
    private ApprovalStatus approvalStatus;
    private List<LoanTransactionDetail> loanTransactionDetails;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
