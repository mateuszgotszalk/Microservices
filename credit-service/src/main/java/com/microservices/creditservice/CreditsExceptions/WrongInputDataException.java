package com.microservices.creditservice.CreditsExceptions;

public class WrongInputDataException extends RuntimeException {

    public WrongInputDataException(String wrongInput) {
        super("Wrong " +wrongInput);
    }
}
