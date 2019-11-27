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

    @Autowired
    private CustomerRepository customerRepository;

    private CustomerList customerList = new CustomerList();

    // writing a customer to database
    @RequestMapping(path = "/createCustomer")
    public void createCustomer(@RequestBody Customer customer){
        customerRepository.save(customer);
    }

    // method returns all customers from database with ids from list
    @RequestMapping(path = "/getCustomers")
    public CustomerList getProducts(@RequestBody List<Integer> list){
        List<Customer> customers = list.stream()
                .map(integer -> customerRepository.findByCreditId(integer))
                .collect(Collectors.toList());
        customerList.setCustomers(customers);
        return customerList;
    }
}
