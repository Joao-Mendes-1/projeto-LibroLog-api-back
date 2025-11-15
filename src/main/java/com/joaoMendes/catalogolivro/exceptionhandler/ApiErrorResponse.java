package com.joaoMendes.catalogolivro.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ApiErrorResponse {

    private String message;
    private List<String> errors;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime timestamp;

    public ApiErrorResponse(String message, List<String> errors, LocalDateTime timestamp) {
        this.message = message;
        this.errors = errors;
        this.timestamp = timestamp;
    }
}
