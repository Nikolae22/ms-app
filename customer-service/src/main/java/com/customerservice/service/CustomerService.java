package com.customerservice.service;

import com.customerservice.dto.CustomerRequest;
import com.customerservice.dto.CustomerResponse;

import java.util.List;

public interface CustomerService {
    String createCustomer(CustomerRequest request);

    void updateCustomer(CustomerRequest customerRequest);

    List<CustomerResponse> findAllCustomers();

    boolean existsById(String customerId);

    CustomerResponse findById(String customerId);

    void deleteById(String customerId);
}
