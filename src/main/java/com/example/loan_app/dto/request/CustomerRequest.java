package com.example.loan_app.dto.request;

import com.example.loan_app.constant.Message;
import com.example.loan_app.entity.User;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerRequest {
    private String id;

    @NotBlank(message = Message.REQUIRED_NAME)
    private String firstName;

    @NotBlank(message = Message.REQUIRED_NAME)
    private String lastName;

    private Date dob;

    @NotBlank(message = Message.REQUIRED_PHONE)
    private String phone;
    private String status;
    private User user;
}
