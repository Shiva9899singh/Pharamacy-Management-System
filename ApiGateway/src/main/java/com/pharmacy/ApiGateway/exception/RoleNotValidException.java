package com.pharmacy.ApiGateway.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class RoleNotValidException extends RuntimeException{

    public RoleNotValidException(String message) {
        super(message);
    }

}
