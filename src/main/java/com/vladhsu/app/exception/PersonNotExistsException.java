package com.vladhsu.app.exception;

public class PersonNotExistsException extends RuntimeException {
    public PersonNotExistsException(String message) {
        super(message);
    }
}
