package com.microservices.customerservice.repositories;

import com.microservices.customerservice.models.Customer;
import org.springframework.data.repository.CrudRepository;


public interface CustomerRepository extends CrudRepository<Customer, String> {
    Customer findByCreditId(Integer id);
}
