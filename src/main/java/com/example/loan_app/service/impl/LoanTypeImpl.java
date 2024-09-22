package com.example.loan_app.service.impl;

import com.example.loan_app.dto.request.LoanTypeRequest;
import com.example.loan_app.entity.LoanType;
import com.example.loan_app.repository.LoanTypeRepository;
import com.example.loan_app.service.LoanTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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
        Optional<LoanType> loanType = loanTypeRepository.findById(id);
        return loanType.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Loan type not found"));
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
