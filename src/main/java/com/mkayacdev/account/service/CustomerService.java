package com.mkayacdev.account.service;

import com.mkayacdev.account.dto.CustomerDto;
import com.mkayacdev.account.dto.CustomerDtoConverter;
import com.mkayacdev.account.exception.CustomerNotFoundException;
import com.mkayacdev.account.model.Customer;
import com.mkayacdev.account.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerDtoConverter converter;

    public CustomerService(CustomerRepository customerRepository, CustomerDtoConverter converter) {
        this.customerRepository = customerRepository;
        this.converter = converter;
    }

    protected Customer findCustomerById(String id){
        return customerRepository.findById(id).orElseThrow(() ->
                new CustomerNotFoundException("Customer could not find by id :"+id));
    }

    public CustomerDto getCustomerById(String customerId) {
        return converter.convertToCustomerDto(findCustomerById(customerId));
    }
}
