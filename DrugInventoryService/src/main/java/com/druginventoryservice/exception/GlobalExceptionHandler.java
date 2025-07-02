package com.druginventoryservice.exception;

import com.druginventoryservice.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Duplicate Drug Exception
    @ExceptionHandler(DuplicateDrugException.class)
    public ResponseEntity<?> handleDuplicateDrugException(DuplicateDrugException ex, WebRequest request)
    {
        ErrorResponse errorDetails = new ErrorResponse( new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    // Drugs Creation Exception
    @ExceptionHandler(DrugCreationException.class)
    public ResponseEntity<?> handleDrugsNotFoundException(DrugCreationException ex, WebRequest request)
    {
        ErrorResponse errorDetails = new ErrorResponse( new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    // Drugs Not Found Exception
    @ExceptionHandler(DrugsNotFoundException.class)
    public ResponseEntity<?> handleDrugsNotFoundException(DrugsNotFoundException ex, WebRequest request)
    {
        ErrorResponse errorDetails = new ErrorResponse( new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    // Validation Exception
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }


}
