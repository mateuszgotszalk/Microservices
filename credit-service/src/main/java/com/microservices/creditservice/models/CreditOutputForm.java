package com.microservices.creditservice.models;

// This is a model, which shows a credit with product and customer details
public class CreditOutputForm {

    private Integer id;
    private String creditName;
    private String firstName;
    private String surName;
    private String pesel;
    private String productName;
    private int productValue;



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductValue() {
        return productValue;
    }

    public void setProductValue(int productValue) {
        this.productValue = productValue;
    }

    public String getCreditName() {
        return creditName;
    }

    public void setCreditName(String creditName) {
        this.creditName = creditName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
