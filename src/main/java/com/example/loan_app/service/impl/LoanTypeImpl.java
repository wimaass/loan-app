package com.example.loan_app.service.impl;

import com.example.loan_app.dto.request.LoanTypeRequest;
import com.example.loan_app.entity.LoanType;
import com.example.loan_app.repository.LoanTypeRepository;
import com.example.loan_app.service.LoanTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.loan_app.mapper.LoanTypeMapper.mapToLoanType;

@Service
@RequiredArgsConstructor
public class LoanTypeImpl implements LoanTypeService {
    private final LoanTypeRepository loanTypeRepository;

    @Override
    public LoanType createLoanType(LoanTypeRequest request) {
        LoanType loanType = mapToLoanType(request);
        return loanTypeRepository.save(loanType);
    }

    @Override
    public LoanType getLoanTypeById(String id) {
        return null;
    }

    @Override
    public List<LoanType> getAllLoanTypes() {
        return List.of();
    }

    @Override
    public LoanType updateLoanType(LoanTypeRequest request) {
        return null;
    }

    @Override
    public void deleteLoanType(String id) {

    }
}
