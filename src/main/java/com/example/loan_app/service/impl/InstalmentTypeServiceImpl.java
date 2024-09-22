package com.example.loan_app.service.impl;

import com.example.loan_app.dto.request.InstalmentTypeRequest;
import com.example.loan_app.entity.InstalmentType;
import com.example.loan_app.repository.InstalmentTypeRepository;
import com.example.loan_app.service.InstalmentTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static com.example.loan_app.mapper.InstalmentTypeMapper.mapToInstalmentType;

@Service
@RequiredArgsConstructor
public class InstalmentTypeServiceImpl implements InstalmentTypeService {
    private final InstalmentTypeRepository instalmentTypeRepository;

    @Override
    public InstalmentType createInstalmentType(InstalmentTypeRequest request) {
        try{
            InstalmentType instalmentType = mapToInstalmentType(request);
            return instalmentTypeRepository.save(instalmentType);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
