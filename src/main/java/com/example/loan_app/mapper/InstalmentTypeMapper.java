package com.example.loan_app.mapper;

import com.example.loan_app.dto.request.InstalmentTypeRequest;
import com.example.loan_app.entity.EInstalmentType;
import com.example.loan_app.entity.InstalmentType;

public class InstalmentTypeMapper {
    public static InstalmentType mapToInstalmentType(InstalmentTypeRequest request) {
        return InstalmentType.builder()
                .instalmentType(Enum.valueOf(EInstalmentType.class, request.getInstalmentType().toUpperCase()))
                .build();
    }
}
