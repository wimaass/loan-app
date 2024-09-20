package com.example.loan_app.dto.request;

import com.example.loan_app.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminRequest {
    private String id;
    private String firstName;
    private String lastName;
    private Date dob;
    private String phone;
    private String status;
    private User user;
}
