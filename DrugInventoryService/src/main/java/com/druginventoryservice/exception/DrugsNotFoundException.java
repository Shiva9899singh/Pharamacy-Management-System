package com.druginventoryservice.exception;

public class DrugsNotFoundException extends RuntimeException{

    public DrugsNotFoundException(String message) {
        super(message);
    }
}
