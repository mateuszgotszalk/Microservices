package com.microservices.creditservice.models;

import java.util.List;
import java.util.stream.Collectors;

public class MergeModels {

    public static List<CreditOutputForm> setFinalList(CreditList credits, ProductList productList, CustomerList customerList){

        List<CreditOutputForm> finalList = credits.getCredits().stream()
                .map(credit -> {
                    CreditOutputForm creditOutputForm = new CreditOutputForm();
                    creditOutputForm.setId(credit.getId());
                    creditOutputForm.setCreditName(credit.getCreditName());
                    return creditOutputForm;
                }).collect(Collectors.toList());

        for (CreditOutputForm item: finalList){
            int idCredit = item.getId();
            for (Product product: productList.getProducts()){
                if(product.getCreditId().equals(idCredit)){
                    item.setProductName(product.getProductName());
                    item.setProductValue(product.getProductValue());
                    break;
                }
            }
            for (Customer customer: customerList.getCustomers()) {
                if(customer.getCreditId().equals(idCredit)){
                    item.setFirstName(customer.getFirstName());
                    item.setSurName(customer.getSurName());
                    item.setPesel(customer.getPesel());
                    break;
                }
            }
        }

        return finalList;
    }
}
