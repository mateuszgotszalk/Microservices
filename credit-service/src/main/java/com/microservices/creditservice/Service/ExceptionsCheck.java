package com.microservices.creditservice.Service;

import com.microservices.creditservice.models.CreditInputForm;
import org.springframework.stereotype.Service;

@Service
public class ExceptionsCheck extends  Exception{

    public static void checkerData(CreditInputForm form) throws Exception {
        if (form.getCreditName()==null){
            throw new Exception("Wrong credit name");
        }
        if (form.getFirstName()==null){
            throw new Exception("Wrong firstname");
        }
        if (form.getSurName()==null){
            throw new Exception("Wrong surname");
        }
        if (form.getPesel()==null){
            throw new Exception("Wrong pesel");
        }
        if (form.getProductName()==null){
            throw new Exception("Wrong product name");
        }
        if (form.getProductValue()==0){
            throw new Exception("Wrong product value");
        }
    }
}
