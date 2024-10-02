package com.example.loan_app.dto.request;

import com.example.loan_app.constant.Message;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InstalmentTypeRequest {
    private String id;

    @NotBlank(message = Message.REQUIRED_INSTALMENT_TYPE)
    private String instalmentType;
}
