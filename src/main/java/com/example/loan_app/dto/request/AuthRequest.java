package com.example.loan_app.dto.request;

import com.example.loan_app.constant.Message;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthRequest {
    @Email(message = Message.INCORRECT_AUTH)
    @NotBlank(message = Message.REQUIRED_EMAIL)
    private String email;

    @NotBlank(message = "password is required")
    @Size(min = 8, message = "Password must be greather than 7")
    private String password;
}
