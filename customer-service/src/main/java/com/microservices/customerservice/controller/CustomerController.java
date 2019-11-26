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

    @RequestMapping(path = "/createCustomer")
    public void createCustomer(@RequestBody Customer customer){
        customerRepository.save(customer);
    }

    @RequestMapping(path = "/getCustomers")
    public CustomerList getProducts(@RequestBody List<Integer> list){
        List<Customer> products = list.stream()
                .map(integer -> customerRepository.findByCreditId(integer))
                .collect(Collectors.toList());
        customerList.setCustomers(products);
        return customerList;
    }
}
