package com.example.loan_app.dto.request;

import com.example.loan_app.constant.Message;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanTransactionRequest {
    @NotBlank(message = Message.REQUIRED_LOAN_TYPE_ID)
    private String loanTypeId;

    @NotBlank(message = Message.REQUIRED_INSTALMENT_TYPE)
    private String instalmentTypeId;

    @NotBlank(message = Message.REQUIRED_CUSTOMER)
    private String customer;

    @NotNull(message = Message.REQUIRED_NOMINAL)
    private Double nominal;
}
