package com.customerservice.service.impl;

import com.customerservice.dto.CustomerRequest;
import com.customerservice.dto.CustomerResponse;
import com.customerservice.entity.Customer;
import com.customerservice.exception.CustomerNotFoundException;
import com.customerservice.mapper.CustomerMapper;
import com.customerservice.repository.CustomerRepository;
import com.customerservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper mapper;

    @Override
    public String createCustomer(CustomerRequest request) {
        var customer=customerRepository.save(mapper.toCustomer(request));
        return customer.getId();
    }

    @Override
    public void updateCustomer(CustomerRequest customerRequest) {

        var customer=customerRepository.findById(customerRequest.id())
                .orElseThrow(()-> new CustomerNotFoundException(
                        "User not found"
                ));
        mergerCustomer(customer,customerRequest);
        customerRepository.save(customer);
    }

    @Override
    public List<CustomerResponse> findAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(mapper::fromCustomer)
                .toList();
    }

    @Override
    public boolean existsById(String customerId) {
        return customerRepository.findById(customerId)
                .isPresent();
    }

    @Override
    public CustomerResponse findById(String customerId) {
        return customerRepository.findById(customerId)
                .map(mapper::fromCustomer)
                .orElseThrow(()->new CustomerNotFoundException("No customer with this id "+customerId));
    }

    @Override
    public void deleteById(String customerId) {
        customerRepository.deleteById(customerId);
    }

    private void mergerCustomer(Customer customer, CustomerRequest customerRequest) {
        if (StringUtils.isNotBlank(customerRequest.firstName())){
            customer.setFirstName(customerRequest.firstName());
        }
        if (StringUtils.isNotBlank(customerRequest.lastName())){
            customer.setLastName(customerRequest.lastName());
        }
        if (StringUtils.isNotBlank(customerRequest.email())){
            customer.setEmail(customerRequest.email());
        }
        if (customerRequest.address() !=null){
            customer.setAddress(customerRequest.address());
        }
    }
}
