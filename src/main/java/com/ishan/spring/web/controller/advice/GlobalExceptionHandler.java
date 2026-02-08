package com.ishan.spring.web.controller.advice;

import com.ishan.spring.web.dto.ErrorDto;
import com.ishan.spring.web.exception.OwnerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import jakarta.validation.ConstraintViolationException;


import java.time.LocalDateTime;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    // handles business logic exception OwnerNotFoundException
    @ExceptionHandler
    public ResponseEntity<ErrorDto> handleOwnerNotFoundException(OwnerNotFoundException ex) {
        String errorMessage = ex.getMessage();
        HttpStatus statusCode = HttpStatus.NOT_FOUND;
        int statusCodeValue = HttpStatus.NOT_FOUND.value();
        LocalDateTime timestamp = LocalDateTime.now();
        ErrorDto errorDto = ErrorDto.builder().message(errorMessage).statusCode(statusCode).statusCodeValue(statusCodeValue).timestamp(timestamp).build();
        return ResponseEntity.status(errorDto.getStatusCode()).body(errorDto);
    }

    // handles validation exception ConstraintViolationException in request params
    @ExceptionHandler
    public ResponseEntity<ErrorDto> handleConstraintViolationExceptionInRequestParams(ConstraintViolationException ex) {;
        String message = ex.getMessage();
        HttpStatus statusCode = HttpStatus.BAD_REQUEST;
        int statusCodeValue = HttpStatus.BAD_REQUEST.value();
        LocalDateTime timestamp = LocalDateTime.now();
        ErrorDto errorDto = ErrorDto.builder().message(message).statusCode(statusCode).statusCodeValue(statusCodeValue).timestamp(timestamp).build();
        return ResponseEntity.status(statusCode).body(errorDto);
    }

    // handles validation exception MethodArgumentNotValidException in request body
    @ExceptionHandler
    public ResponseEntity<ErrorDto> handleMethodArgumentNotValidExceptionInRequestBody(MethodArgumentNotValidException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldError();
        String errorMessage = fieldError != null ? fieldError.getDefaultMessage() : "Validation failed";
        
        HttpStatus statusCode = HttpStatus.BAD_REQUEST;
        int statusCodeValue = HttpStatus.BAD_REQUEST.value();
        LocalDateTime timestamp = LocalDateTime.now();
        ErrorDto errorDto = ErrorDto.builder().message(errorMessage).statusCode(statusCode).statusCodeValue(statusCodeValue).timestamp(timestamp).build();
        return ResponseEntity.status(statusCode).body(errorDto);
    }

    // this handles generic errors other than the ones that we have defined in the business logic itself (like OwnerNotFoundException)
    @ExceptionHandler
    public ResponseEntity<ErrorDto> handleGenericException(Exception ex) {
        String errorMessage = ex.getMessage();
        HttpStatus statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
        int statusCodeValue = HttpStatus.INTERNAL_SERVER_ERROR.value();
        LocalDateTime timestamp = LocalDateTime.now();
        ErrorDto errorDto = ErrorDto.builder().message(errorMessage).statusCode(statusCode).statusCodeValue(statusCodeValue).timestamp(timestamp).build();
        return ResponseEntity.status(errorDto.getStatusCode()).body(errorDto);
    }

}
