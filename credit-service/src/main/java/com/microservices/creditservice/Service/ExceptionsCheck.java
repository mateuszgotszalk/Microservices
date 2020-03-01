package com.microservices.creditservice.Service;

import com.microservices.creditservice.CreditsExceptions.WrongInputDataException;
import com.microservices.creditservice.models.CreditInputForm;
import org.springframework.stereotype.Service;

@Service
public class ExceptionsCheck extends  Exception{

    public static void checkerData(CreditInputForm form) throws WrongInputDataException {
        if (form.getCreditName()==null) throw new WrongInputDataException("credit name");
        
        if (form.getFirstName()==null) throw new WrongInputDataException("firstname");

        if (form.getSurName()==null) throw new WrongInputDataException("surname");

        if (form.getPesel()==null) throw new WrongInputDataException("pesel");

        if (form.getProductName()==null) throw new WrongInputDataException("product name");

        if (form.getProductValue()==0) throw new WrongInputDataException("product value");
    }
}