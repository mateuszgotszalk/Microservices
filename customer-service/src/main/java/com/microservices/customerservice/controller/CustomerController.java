package com.microservices.customerservice.controller;

import com.microservices.customerservice.models.Customer;
import com.microservices.customerservice.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping(path = "/createCustomer")
    public void createCustomer(@RequestBody Customer customer){
        customerRepository.save(customer);
    }
}
