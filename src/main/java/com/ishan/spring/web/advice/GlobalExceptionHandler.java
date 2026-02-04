package com.ishan.spring.web.advice;

import com.ishan.spring.web.dto.ErrorDto;
import com.ishan.spring.web.exception.OwnerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorDto> handleOwnerNotFoundException(OwnerNotFoundException ex) {
        String errorMessage = ex.getMessage();
        HttpStatus statusCode = HttpStatus.NOT_FOUND;
        int statusCodeValue = HttpStatus.NOT_FOUND.value();
        LocalDateTime timestamp = LocalDateTime.now();
        ErrorDto errorDto = ErrorDto.builder().message(errorMessage).statusCode(statusCode).statusCodeValue(statusCodeValue).timestamp(timestamp).build();
        return ResponseEntity.status(errorDto.getStatusCode()).body(errorDto);
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
