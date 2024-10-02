package com.example.loan_app.service.impl;

import com.example.loan_app.entity.LoanStatus;
import com.example.loan_app.entity.LoanTransactionDetail;
import com.example.loan_app.repository.LoanTransactionDetailRepository;
import com.example.loan_app.service.LoanTransactionDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanTransactionDetalServiceImpl implements LoanTransactionDetailService {
    private final LoanTransactionDetailRepository loanTransactionDetailRepository;

    @Override
    public List<LoanTransactionDetail> getUnpaid(String id){
        List<LoanTransactionDetail> unpaid = loanTransactionDetailRepository.findByLoanStatusAndLoanTransactionId(LoanStatus.UNPAID, id);
        if (unpaid == null || unpaid.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Already all paid");
        }
        return unpaid;
    }

    @Override
    public LoanTransactionDetail update(LoanTransactionDetail loanTransactionDetail) {
        loanTransactionDetail.setLoanStatus(LoanStatus.PAID);
        loanTransactionDetail.setTransactionDate(LocalDateTime.now());

        return loanTransactionDetailRepository.saveAndFlush(loanTransactionDetail);
    }
}
