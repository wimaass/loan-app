package com.example.loan_app.service;

import com.example.loan_app.dto.request.CustomerRequest;
import com.example.loan_app.dto.response.CustomerResponse;
import com.example.loan_app.entity.Customer;

import java.util.List;

public interface CustomerService {
    CustomerResponse createCustomer(CustomerRequest customerRequest);
    List<CustomerResponse> getAllCustomers();
    CustomerResponse getCustomerById(String id);
    CustomerResponse updateCustomer(CustomerRequest customerRequest);
    void deleteCustomer(String id);

    Customer getCustomer(String id);
}
