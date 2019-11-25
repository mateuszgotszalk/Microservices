package com.microservices.creditservice.repositories;

import com.microservices.creditservice.models.Credit;
import org.springframework.data.repository.CrudRepository;

public interface CreditRepository extends CrudRepository<Credit, Integer> {
}
