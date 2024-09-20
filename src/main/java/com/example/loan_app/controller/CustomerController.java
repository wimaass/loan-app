package com.example.loan_app.controller;

import com.example.loan_app.constant.PathApi;
import com.example.loan_app.dto.request.CustomerRequest;
import com.example.loan_app.dto.response.CommonResponse;
import com.example.loan_app.dto.response.CustomerResponse;
import com.example.loan_app.entity.Customer;
import com.example.loan_app.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.loan_app.mapper.CommonResponseMapper.getCommonResponse;

@RestController
@RequestMapping(PathApi.CUSTOMER_API)
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    private static String message;
    private static HttpStatus statusCode;

    @PostMapping
    ResponseEntity<?> createCustomer(@RequestBody CustomerRequest customerRequest) {
        CustomerResponse customerResponse =  customerService.createCustomer(customerRequest);
        message = "Successfully created customer";
        statusCode = HttpStatus.CREATED;

        CommonResponse<?> response = getCommonResponse(message, statusCode, customerResponse);

        return ResponseEntity
                .status(statusCode)
                .body(response);
    }

    @GetMapping
    ResponseEntity<?> getAllCustomers() {
        List<CustomerResponse> customerResponseList = customerService.getAllCustomers();
        message = "Succcessfully fetched all customers";
        statusCode = HttpStatus.OK;

        CommonResponse<?> response = getCommonResponse(message, statusCode, customerResponseList);

        return ResponseEntity
                .status(statusCode)
                .body(response);
    }

    @PutMapping
    ResponseEntity<?> updateCustomer(@RequestBody CustomerRequest customerRequest) {
        CustomerResponse customerResponse = customerService.updateCustomer(customerRequest);
        message = "Successfully updated customer";
        statusCode = HttpStatus.OK;

        CommonResponse<?> response = getCommonResponse(message, statusCode, customerResponse);

        return ResponseEntity
                .status(statusCode)
                .body(response);
    }

    @DeleteMapping(PathApi.GET_ID)
    ResponseEntity<?> deleteCustomer(@PathVariable String id) {
        customerService.deleteCustomer(id);
        message = "Successfully deleted customer";
        statusCode = HttpStatus.OK;

        CommonResponse<?> response = getCommonResponse(message, statusCode);
        return ResponseEntity
                .status(statusCode)
                .body(response);
    }
}
