package com.example.base.exception;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.base.dto.AuthResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handle validation errors from @Valid @RequestBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<AuthResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fe -> fe.getDefaultMessage())
                .collect(Collectors.joining(", "));

        if (errorMessage.isEmpty()) {
            errorMessage = "Validation error";
        }

        return new ResponseEntity<>(new AuthResponse(null, errorMessage), HttpStatus.BAD_REQUEST);
    }

    // Handle general exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<AuthResponse> handleAllExceptions(Exception ex) {
        return new ResponseEntity<>(new AuthResponse(null, "Server error: " + ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}