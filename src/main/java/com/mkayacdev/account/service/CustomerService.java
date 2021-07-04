package com.mkayacdev.account.service;

import com.mkayacdev.account.exception.CustomerNotFoundException;
import com.mkayacdev.account.model.Customer;
import com.mkayacdev.account.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    protected Customer findCustomerById(String id){
        return customerRepository.findById(id).orElseThrow(() ->
                new CustomerNotFoundException("Customer could not find by id :"+id));
    }

}
