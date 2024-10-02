package com.example.loan_app.dto.request;

import com.example.loan_app.constant.Message;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanTypeRequest {
    private String id;

    @NotBlank(message = Message.REQUIRED_LOAN_TYPE)
    private String type;

    @NotNull(message = Message.REQUIRED_NOMINAL)
    private Double maxLoan;
}
