package com.microservices.productservice.controller;

import com.microservices.productservice.models.Product;
import com.microservices.productservice.models.ProductList;
import com.microservices.productservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductController {

    private final ProductRepository productRepository;

    private ProductList productList = new ProductList();

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // writing a product to database
    @RequestMapping(path = "/createProduct")
    public void createProduct(@RequestBody Product product){
        productRepository.save(product);
    }

    // method returns all products from database with ids from list
    @RequestMapping(path = "/getProducts")
    public ProductList getProducts(@RequestBody List<Integer> list){

        List<Product> products = list.stream()
                .map(productRepository::findByCreditId)
                .collect(Collectors.toList());
        productList.setProducts(products);
        return productList;
    }
}