package com.example.loan_app.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApprovalRequest {
    private String loanTransactionId;
    private int interestRates;
    private String adminId;
}
