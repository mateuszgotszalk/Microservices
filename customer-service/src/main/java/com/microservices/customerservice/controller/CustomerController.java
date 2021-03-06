package com.microservices.customerservice.controller;

import com.microservices.customerservice.models.Customer;
import com.microservices.customerservice.models.CustomerList;
import com.microservices.customerservice.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CustomerController {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    private CustomerList customerList = new CustomerList();

    // writing a customer to database
    @RequestMapping(path = "/createCustomer")
    public void createCustomer(@RequestBody Customer customer){
        customerRepository.save(customer);
    }

    // method returns all customers from database with ids from list
    @RequestMapping(path = "/getCustomers")
    public CustomerList getCustomers(@RequestBody List<Integer> list){
        List<Customer> customers = list.stream()
                .map(customerRepository::findByCreditId)
                .collect(Collectors.toList());
        customerList.setCustomers(customers);
        return customerList;
    }
}