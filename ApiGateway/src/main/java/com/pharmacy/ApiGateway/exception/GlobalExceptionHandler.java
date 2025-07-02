package com.pharmacy.ApiGateway.exception;

import com.pharmacy.ApiGateway.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Invalid Credentials Exception
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<?> handleInvalidCredentialException(InvalidCredentialsException ex, WebRequest request)
    {
        ErrorResponse errorResponse = new ErrorResponse(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // Invalid Credentials Exception
    @ExceptionHandler(RoleNotValidException.class)
    public ResponseEntity<?> handleRoleNotValidException(RoleNotValidException ex, WebRequest request)
    {
        ErrorResponse errorResponse = new ErrorResponse(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }
}
