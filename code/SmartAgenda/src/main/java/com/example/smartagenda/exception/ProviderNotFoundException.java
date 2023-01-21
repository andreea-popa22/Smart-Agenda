package com.example.smartagenda.exception;

public class ProviderNotFoundException extends RuntimeException{
    public ProviderNotFoundException(String message) {
        super(message);
    }
}
