package com.example.loan_app.mapper;

import com.example.loan_app.dto.request.AdminRequest;
import com.example.loan_app.dto.response.AdminResponse;
import com.example.loan_app.entity.Admin;

public class AdminMapper {
    public static Admin mapToAdmin(AdminRequest adminRequest){
        return Admin.builder()
                .firstName(adminRequest.getFirstName())
                .lastName(adminRequest.getLastName())
                .phone(adminRequest.getPhone())
                .dateOfBirth(adminRequest.getDob())
                .user(adminRequest.getUser())
                .status(adminRequest.getStatus())
                .build();
    }

    public static AdminResponse mapToAdminResponse(Admin admin){
        return AdminResponse.builder()
                .id(admin.getId())
                .firstName(admin.getFirstName())
                .lastName(admin.getLastName())
                .phone(admin.getPhone())
                .dob(admin.getDateOfBirth())
                .status(admin.getStatus())
                .build();
    }
}
