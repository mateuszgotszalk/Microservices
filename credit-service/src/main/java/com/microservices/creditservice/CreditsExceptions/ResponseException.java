package com.microservices.creditservice.CreditsExceptions;

public class ResponseException extends RuntimeException {

    public ResponseException() {
        super("Something went wrong on the server");
    }
}
