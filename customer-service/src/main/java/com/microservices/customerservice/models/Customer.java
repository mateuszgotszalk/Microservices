package com.microservices.customerservice.models;

import javax.persistence.*;
/* A simple model of credit writing to database*/
@Entity
public class Customer {

    private Integer creditId;
    private String firstName;
    private String surName;

    /* This annotation tells Spring's this is a primary key for Customer*/
    @Id
    private String pesel;

    public Integer getCreditId() {
        return creditId;
    }

    public void setCreditId(Integer creditId) {
        this.creditId = creditId;
    }

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
}
