package com.example.loan_app.service;

import com.example.loan_app.dto.request.InstalmentTypeRequest;
import com.example.loan_app.entity.InstalmentType;

import java.util.List;

public interface InstalmentTypeService {
    InstalmentType createInstalmentType(InstalmentTypeRequest request);
    InstalmentType getInstalmentTypeById(String id);
    List<InstalmentType> getAllInstalmentTypes();
    InstalmentType updateInstalmentType(InstalmentTypeRequest request);
    void deleteInstalmentTypeById(String id);
}
