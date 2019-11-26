package com.microservices.productservice.controller;

import com.microservices.productservice.models.Product;
import com.microservices.productservice.models.ProductList;
import com.microservices.productservice.repositories.ProductRepository;
import org.hibernate.validator.constraints.Mod11Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    private ProductList productList = new ProductList();

    @RequestMapping(path = "/createProduct")
    public void createProduct(@RequestBody Product product){
        productRepository.save(product);
    }

    @RequestMapping(path = "/getProducts")
    public ProductList getProducts(@RequestBody List<Integer> list){

        List<Product> products = list.stream()
                .map(integer -> productRepository.findByCreditId(integer))
                .collect(Collectors.toList());
        productList.setProducts(products);
        return productList;
    }
}