package com.example.loan_app.dto.request;

import jakarta.validation.constraints.NotBlank;
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

    @NotBlank
    private String type;

    @NotBlank
    private Double maxLoan;
}
