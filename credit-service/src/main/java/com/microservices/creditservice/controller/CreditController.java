package com.microservices.creditservice.controller;


import com.microservices.creditservice.models.*;
import com.microservices.creditservice.repositories.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CreditController {

    private RestTemplate restTemplate = new RestTemplate();

    private final CreditRepository creditRepository;

    @Autowired
    public CreditController(CreditRepository creditRepository) {
        this.creditRepository = creditRepository;
    }

    // method with CreditInputForm parameter field and return credits id
    @RequestMapping(path = "/createCredit")
    public int createCredit(@RequestBody CreditInputForm creditInputForm){

        // new credit writing to database
        Credit credit = new Credit();
        credit.setCreditName(creditInputForm.getCreditName());
        creditRepository.save(credit);

        // new customer sending to customer-service
        Customer customer = new Customer();
        customer.setCreditId(credit.getId());
        customer.setPesel(creditInputForm.getPesel());
        customer.setFirstName(creditInputForm.getFirstName());
        customer.setSurName(creditInputForm.getSurName());

        // sending customer
        restTemplate.postForObject("http://customerservice:3302/createCustomer", customer, Customer.class);

        // new product sending to product-service
        Product product = new Product();
        product.setCreditId(credit.getId());
        product.setProductName(creditInputForm.getProductName());
        product.setProductValue(creditInputForm.getProductValue());

        // sending product
        restTemplate.postForObject("http://productservice:3303/createProduct", product, Product.class);

        return credit.getId();
    }

    // method return credits with customers and product as a list
    @GetMapping(path = "/getCredits")
    public @ResponseBody List<CreditOutputForm> getCredits(){

        //downloading credits from database
        CreditList creditList = new CreditList.Builder()
                .credits((List<Credit>) creditRepository.findAll())
                .build();

        // making a list of credit ids
        List<Integer> ids = creditList.getCredits().stream()
                .map(Credit::getId)
                .collect(Collectors.toList());

        //sending a list of ids and return a list of products
        ProductList productList = restTemplate.postForObject
                ("http://productservice:3303/getProducts", ids, ProductList.class);

        //sending a list of ids and return a list of customers
        CustomerList customerList = restTemplate.postForObject
                ("http://customerservice:3302/getCustomers", ids, CustomerList.class);

        // return a list of merge credits with customers and products
        return MergeModels
                .setFinalList(creditList, productList, customerList);
    }
}