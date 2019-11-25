package com.microservices.creditservice.controller;


import com.microservices.creditservice.models.*;
import com.microservices.creditservice.repositories.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CreditController {

    RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private CreditRepository creditRepository;


    @RequestMapping(path = "/createCredit")
    public int createCredit(@RequestBody CreditInputForm creditInputForm){


        Credit credit = new Credit();
        credit.setCreditName(creditInputForm.getCreditName());
        creditRepository.save(credit);

        Customer customer = new Customer();
        customer.setCreditId(credit.getId());
        customer.setPesel(creditInputForm.getPesel());
        customer.setFirstName(creditInputForm.getFirstName());
        customer.setSurName(creditInputForm.getSurName());

        restTemplate.postForObject("http://localhost:3302/createCustomer", customer, Customer.class);

        Product product = new Product();
        product.setCreditId(credit.getId());
        product.setProductName(creditInputForm.getProductName());
        product.setProductValue(creditInputForm.getProductValue());

        restTemplate.postForObject("http://localhost:3303/createProduct", product, Product.class);

        return credit.getId();
    }

    @GetMapping(path = "/getCredits")
    public @ResponseBody List<Credit> getCredits(){
        List<Credit> credits = (List<Credit>)creditRepository.findAll();
        List<String> ids = credits.stream()
                .map(credit -> (credit.getId()+""))
                .collect(Collectors.toList());
        restTemplate.postForObject("http://localhost:3303/getProducts", ids, List.class);

        return (List<Credit>)creditRepository.findAll();
    }
}
