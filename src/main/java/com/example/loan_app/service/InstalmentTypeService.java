package com.example.loan_app.service;

import com.example.loan_app.dto.request.InstalmentTypeRequest;
import com.example.loan_app.entity.InstalmentType;

public interface InstalmentTypeService {
    InstalmentType createInstalmentType(InstalmentTypeRequest request);
}
