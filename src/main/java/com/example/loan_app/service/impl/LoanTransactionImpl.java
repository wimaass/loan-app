package com.example.loan_app.service.impl;

import com.example.loan_app.constant.Message;
import com.example.loan_app.dto.request.ApprovalRequest;
import com.example.loan_app.dto.request.LoanTransactionRequest;
import com.example.loan_app.dto.response.LoanTransactionResponse;
import com.example.loan_app.entity.*;
import com.example.loan_app.mapper.LoanTransactionMapper;
import com.example.loan_app.repository.LoanTransactionDetailRepository;
import com.example.loan_app.repository.LoanTransactionRepository;
import com.example.loan_app.service.*;
import com.example.loan_app.util.ValidationUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.loan_app.mapper.LoanTransactionMapper.mapToLoanTransactionResponse;

@Service
@RequiredArgsConstructor
public class LoanTransactionImpl implements LoanTransactionService {
    private final LoanTransactionRepository loanTransactionRepository;
    private final LoanTypeService loanTypeService;
    private final InstalmentTypeService instalmentTypeService;
    private final CustomerService customerService;
    private final LoanTransactionDetailRepository loanTransactionDetailRepository;
    private final UserService userService;
    private final ValidationUtil validationUtil;
    private final LoanTransactionDetailService loanTransactionDetailService;

    @Transactional(rollbackOn = Exception.class)
    @Override
    public LoanTransactionResponse createLoanTransaction(LoanTransactionRequest request) {
        validationUtil.validate(request);
        LoanType loanType = loanTypeService.getLoanTypeById(request.getLoanTypeId());
        InstalmentType instalmentType = instalmentTypeService.getInstalmentTypeById(request.getInstalmentTypeId());
        Customer customer = customerService.getCustomer(request.getCustomer());

        LoanTransaction loanTransaction = LoanTransactionMapper.mapToLoanTransaction(loanType, instalmentType, customer, request);
        loanTransaction.setApprovalStatus(ApprovalStatus.REJECTED);
        loanTransactionRepository.saveAndFlush(loanTransaction);

        return mapToLoanTransactionResponse(loanTransaction);
    }

    @Override
    public LoanTransactionResponse getLoanTransactionById(String id) {
        LoanTransaction loanTransaction = getLoanTransactionResponseOrThrowNotFound(id);
        return mapToLoanTransactionResponse(loanTransaction);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public LoanTransactionResponse approveLoanTransactionByAdmin(ApprovalRequest request) {
        validationUtil.validate(request);
        AppUser user = userService.loadUserByUserId(request.getAdminId());

        LoanTransaction loanTransaction = getLoanTransactionResponseOrThrowNotFound(request.getLoanTransactionId());
        if(loanTransaction.getApprovalStatus().equals(ApprovalStatus.APPROVED)){
            System.out.println("Approved loan transaction");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Already approved");
        }
        loanTransaction.setApprovalStatus(ApprovalStatus.APPROVED);
        loanTransaction.setApprovedAt(LocalDateTime.now());
        loanTransaction.setApprovedBy(user.getEmail());

        List<LoanTransactionDetail> loanTransactionDetails = new ArrayList<>();
        int month = loanTransaction.getInstalmentType().getInstalmentType().getMonth();
        double interestRates = (double) request.getInterestRates() / 100;
        Double nominalPerMonth = loanTransaction.getNominal()+(loanTransaction.getNominal()/month * interestRates);
        for (int i = 0; i < month; i++) {
            LoanTransactionDetail loanTransactionDetail = new LoanTransactionDetail();
            loanTransactionDetail.setNominal(nominalPerMonth);
            loanTransactionDetail.setLoanTransaction(loanTransaction);
            loanTransactionDetail.setLoanStatus(LoanStatus.UNPAID);

            loanTransactionDetailRepository.saveAndFlush(loanTransactionDetail);
            loanTransactionDetails.add(loanTransactionDetail);
        }

        loanTransaction.setLoanTransactionDetails(loanTransactionDetails);
        loanTransactionRepository.saveAndFlush(loanTransaction);

        return mapToLoanTransactionResponse(loanTransaction);
    }

    @Override
    public LoanTransactionResponse payLoanTransaction(String id) {
        LoanTransaction loanTransaction = getLoanTransactionResponseOrThrowNotFound(id);
        List<LoanTransactionDetail> loanTransactionDetails = loanTransactionDetailService.getUnpaid(id);
        loanTransactionDetailService.update(loanTransactionDetails.get(0));

        return mapToLoanTransactionResponse(loanTransaction);
    }

    private LoanTransaction getLoanTransactionResponseOrThrowNotFound(String id) {
        Optional<LoanTransaction> loanTransaction = loanTransactionRepository.findById(id);
        return loanTransaction.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction " + Message.NOT_FOUND));
    }


}
