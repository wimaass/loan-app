package com.example.loan_app.service.impl;

import com.example.loan_app.constant.Message;
import com.example.loan_app.dto.request.LoanTransactionRequest;
import com.example.loan_app.dto.response.CustomerResponse;
import com.example.loan_app.dto.response.LoanTransactionResponse;
import com.example.loan_app.entity.*;
import com.example.loan_app.mapper.LoanTransactionMapper;
import com.example.loan_app.repository.LoanTransactionRepository;
import com.example.loan_app.service.CustomerService;
import com.example.loan_app.service.InstalmentTypeService;
import com.example.loan_app.service.LoanTransactionService;
import com.example.loan_app.service.LoanTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoanTransactionImpl implements LoanTransactionService {
    private final LoanTransactionRepository loanTransactionRepository;
    private final LoanTypeService loanTypeService;
    private final InstalmentTypeService instalmentTypeService;
    private final CustomerService customerService;

    @Override
    public LoanTransactionResponse createLoanTransaction(LoanTransactionRequest request) {
        LoanType loanType = loanTypeService.getLoanTypeById(request.getLoanTypeId());
        InstalmentType instalmentType = instalmentTypeService.getInstalmentTypeById(request.getInstalmentTypeId());
        Customer customer = customerService.getCustomer(request.getCustomer());

        LoanTransaction loanTransaction = LoanTransactionMapper.mapToLoanTransaction(loanType, instalmentType, customer, request);
        loanTransaction.setApprovalStatus(ApprovalStatus.REJECTED);
        loanTransactionRepository.saveAndFlush(loanTransaction);

        return LoanTransactionMapper.mapToLoanTransactionResponse(loanTransaction);
    }

    @Override
    public LoanTransactionResponse getLoanTransactionById(String id) {
        LoanTransaction loanTransaction = getLoanTransactionResponseOrThrowNotFound(id);
        return LoanTransactionMapper.mapToLoanTransactionResponse(loanTransaction);
    }

    private LoanTransaction getLoanTransactionResponseOrThrowNotFound(String id) {
        Optional<LoanTransaction> loanTransaction = loanTransactionRepository.findById(id);
        return loanTransaction.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction " + Message.NOT_FOUND));
    }
}
