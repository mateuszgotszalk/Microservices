package com.microservices.productservice.controller;

import com.microservices.productservice.models.Product;
import com.microservices.productservice.repositories.ProductRepository;
import org.hibernate.validator.constraints.Mod11Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @RequestMapping(path = "/createProduct")
    public void createProduct(@RequestBody Product product){
        productRepository.save(product);
    }

    @GetMapping(path = "/getProducts")
    public List<Product> getProducts(List<String> list){
        List<Product> products = (List<Product>)productRepository.findAllById(list);
        return products;
    }
}