package com.microservices.creditservice.models;

public class Customer {

    private String firstName;
    private String surName;
    private String pesel;
    private Integer creditId;


    public String getFirstName() {
        return firstName;
    }

    public String getSurName() {
        return surName;
    }

    public String getPesel() {
        return pesel;
    }

    public Integer getCreditId() {
        return creditId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public void setCreditId(Integer creditId) {
        this.creditId = creditId;
    }
}
