package com.microservices.creditservice.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/* A simple model of credit writing to database*/
@Entity
public class Credit {

/* These two annotations tells Spring's this is a primary key with autoincrementation*/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String creditName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreditName() {
        return creditName;
    }

    public void setCreditName(String creditName) {
        this.creditName = creditName;
    }
}
