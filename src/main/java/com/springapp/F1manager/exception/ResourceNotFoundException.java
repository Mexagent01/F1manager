package com.springapp.F1manager.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message,cause);
    }


}
