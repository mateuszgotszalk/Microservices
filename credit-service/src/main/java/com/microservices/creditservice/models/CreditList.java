package com.microservices.creditservice.models;

import java.util.List;

public class CreditList {
    private List<Credit> credits;

    private CreditList(Builder builder) {
        this.credits = builder.credits;
    }

    public List<Credit> getCredits() {
        return credits;
    }

    public static class Builder{
        private List<Credit> credits;

        public Builder credits(List<Credit> credits){
            this.credits = credits;
            return this;
        }

        public CreditList build(){
            return new CreditList(this);
        }
    }
}
