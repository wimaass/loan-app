package com.example.loan_app.service.impl;

import com.example.loan_app.dto.request.CustomerRequest;
import com.example.loan_app.dto.response.CustomerResponse;
import com.example.loan_app.entity.Customer;
import com.example.loan_app.mapper.CustomerMapper;
import com.example.loan_app.repository.CustomerRepository;
import com.example.loan_app.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.loan_app.mapper.CustomerMapper.mapToCustomer;
import static com.example.loan_app.mapper.CustomerMapper.mapToCustomerResponse;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        Customer customer = mapToCustomer(customerRequest);
        customerRepository.saveAndFlush(customer);

        return mapToCustomerResponse(customer);
    }

    @Override
    public List<CustomerResponse> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(CustomerMapper::mapToCustomerResponse).toList();
    }
}
