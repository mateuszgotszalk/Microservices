package com.microservices.productservice.repositories;

import com.microservices.productservice.models.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, String> {
    Product findByCreditId(Integer id);
}
