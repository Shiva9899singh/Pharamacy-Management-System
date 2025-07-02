package com.druginventoryservice.exception;

public class DrugCreationException extends RuntimeException{

    public DrugCreationException(String message) {
        super(message);
    }

    public DrugCreationException(String message, Throwable cause) {
        super(message,cause);
    }

}
