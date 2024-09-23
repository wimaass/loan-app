package com.example.loan_app.service.impl;

import com.example.loan_app.dto.request.CustomerRequest;
import com.example.loan_app.dto.response.CustomerResponse;
import com.example.loan_app.entity.Customer;
import com.example.loan_app.mapper.CustomerMapper;
import com.example.loan_app.repository.CustomerRepository;
import com.example.loan_app.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    @Override
    public CustomerResponse getCustomerById(String id) {
        Customer customer = getCustomerOrThrowNotFound(id);
        return mapToCustomerResponse(customer);
    }

    @Override
    public CustomerResponse updateCustomer(CustomerRequest customerRequest) {
        Customer customer = getCustomerOrThrowNotFound(customerRequest.getId());

        customer.setFirstName(customerRequest.getFirstName());
        customer.setLastName(customerRequest.getLastName());
        customer.setPhone(customerRequest.getPhone());
        customer.setDateOfBirth(customerRequest.getDob());
        customer.setStatus(customerRequest.getStatus());

        customerRepository.save(customer);

        return CustomerResponse.builder()
                .id(customer.getId())
                .build();
    }

    @Override
    public void deleteCustomer(String id) {
        Customer customer = getCustomerOrThrowNotFound(id);
        customerRepository.delete(customer);
    }

    @Override
    public Customer getCustomer(String id) {
        return getCustomerOrThrowNotFound(id);
    }

    private Customer getCustomerOrThrowNotFound(String id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer Not Found"));
    }
}
