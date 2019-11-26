package com.microservices.creditservice.models;


public class Product {

    private String productName;
    private int productValue;
    private Integer creditId;


    public String getProductName() {
        return productName;
    }

    public int getProductValue() {
        return productValue;
    }

    public Integer getCreditId() {
        return creditId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductValue(int productValue) {
        this.productValue = productValue;
    }

    public void setCreditId(Integer creditId) {
        this.creditId = creditId;
    }
}
