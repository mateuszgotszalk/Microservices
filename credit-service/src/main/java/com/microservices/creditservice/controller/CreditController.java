package com.microservices.creditservice.controller;


import com.microservices.creditservice.CreditsExceptions.ResponseException;
import com.microservices.creditservice.CreditsExceptions.WrongInputDataException;
import com.microservices.creditservice.Service.ExceptionsCheck;
import com.microservices.creditservice.models.*;
import com.microservices.creditservice.repositories.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@Transactional
public class CreditController {

    private RestTemplate restTemplate = new RestTemplate();

    private final CreditRepository creditRepository;

    @Autowired
    public CreditController(CreditRepository creditRepository) {
        this.creditRepository = creditRepository;
    }

    // method with CreditInputForm parameter field and return credits id
    @RequestMapping(path = "/createCredit")
    public ResponseEntity<String> createCredit(@RequestBody CreditInputForm creditInputForm){

        try{
            ExceptionsCheck.checkerData(creditInputForm);
        }catch(WrongInputDataException e){
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }

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

        try {
            // sending customer
            restTemplate.postForObject("http://customerservice:3302/createCustomer", customer, Customer.class);
        }catch(RuntimeException e){
            System.out.println(e.getMessage());
            System.out.println("Wrong connection to customer service");
            return ResponseEntity.status(500).body("Wrong connection to customer service");
        }

        // new product sending to product-service
        Product product = new Product();
        product.setCreditId(credit.getId());
        product.setProductName(creditInputForm.getProductName());
        product.setProductValue(creditInputForm.getProductValue());

        // sending product
        try {
            restTemplate.postForObject("http://productservice:3303/createProduct", product, Product.class);
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
            System.out.println("Wrong connection to product service");
            return ResponseEntity.status(500).body("Wrong connection to product service");
        }

        return ResponseEntity.ok().body(credit.getId()+"");
    }

    // method return credits with customers and product as a list
    @GetMapping(path = "/getCredits")
    public @ResponseBody ResponseEntity<List<CreditOutputForm>> getCredits(){

        CreditList creditList=null;
        ProductList productList = null;
        CustomerList customerList= null;

        //downloading credits from database
        //try {
            creditList = new CreditList.Builder()
                    .credits((List<Credit>) creditRepository.findAll())
                    .build();
        /*}catch (RuntimeException e){
            System.out.println(e.getMessage());
        }*/

        // making a list of credit ids
        List<Integer> ids = creditList.getCredits().stream()
                .map(Credit::getId)
                .collect(Collectors.toList());

        //sending a list of ids and return a list of products
//        try {
            productList = restTemplate.postForObject
                    ("http://productservice:3303/getProducts", ids, ProductList.class);
        /*}catch (Exception e){
            e.printStackTrace();
            System.out.println("Something goes wrong with product service");
        }*/

        //try {
        //sending a list of ids and return a list of customers
        customerList = restTemplate.postForObject
                ("http://customerservice:3302/getCustomers", ids, CustomerList.class);
        /*}catch (Exception e){
            e.printStackTrace();
            System.out.println("Something goes wrong with customer service");
        }*/

        // return a list of merge credits with customers and products
        return Optional.of(ResponseEntity
                .ok()
                .body(MergeModels.setFinalList(creditList, productList, customerList)))
                .orElseThrow(ResponseException::new);
    }
}