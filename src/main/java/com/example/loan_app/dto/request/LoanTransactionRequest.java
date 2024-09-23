package com.example.loan_app.dto.request;

import com.example.loan_app.entity.ApprovalStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanTransactionRequest {
    private String loanTypeId;
    private String instalmentTypeId;
    private String customer;
    private Double nominal;
}
