package com.microservices.creditservice.controller;


import com.microservices.creditservice.models.Credit;
import com.microservices.creditservice.repositories.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CreditController {

    @Autowired
    private CreditRepository creditRepository;

    @PostMapping(path = "/createCredit")
    public @ResponseBody int createCredit(@RequestBody String creditName){
        Credit credit = new Credit();
        credit.setCreditName(creditName);
        creditRepository.save(credit);
        return credit.getId();
    }

    @GetMapping(path = "/getCredits")
    public @ResponseBody List<Credit> getCredits(){
        return (List<Credit>)creditRepository.findAll();
    }
}
