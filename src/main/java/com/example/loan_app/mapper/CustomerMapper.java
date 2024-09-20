package com.example.loan_app.mapper;

import com.example.loan_app.dto.request.CustomerRequest;
import com.example.loan_app.dto.response.CustomerResponse;
import com.example.loan_app.entity.Customer;

public class CustomerMapper {
    public static Customer mapToCustomer(CustomerRequest customerRequest) {
        return Customer.builder()
                .firstName(customerRequest.getFirstName())
                .lastName(customerRequest.getLastName())
                .phone(customerRequest.getPhone())
                .dateOfBirth(customerRequest.getDob())
                .status(customerRequest.getStatus())
                .build();
    }

    public static CustomerResponse mapToCustomerResponse(Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .phone(customer.getPhone())
                .status(customer.getStatus())
                .dob(customer.getDateOfBirth())
                .build();
    }

    public static CustomerRequest mapToCustomerRequest(Customer customer) {
        return CustomerRequest.builder()
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .phone(customer.getPhone())
                .status(customer.getStatus())
                .dob(customer.getDateOfBirth())
                .user(customer.getUser())
                .build();
    }
}
