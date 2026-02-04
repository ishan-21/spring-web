package com.ishan.spring.web.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ErrorDto {
    private String message;
    private HttpStatus statusCode;
    private int statusCodeValue;
    private LocalDateTime timestamp;
}
