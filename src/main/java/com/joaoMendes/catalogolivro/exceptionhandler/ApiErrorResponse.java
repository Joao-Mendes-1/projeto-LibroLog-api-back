package com.joaoMendes.catalogolivro.exceptionhandler;

import lombok.Data;
import java.util.List;

@Data
public class ApiErrorResponse {

    private String message;
    private List<String> errors;

    public ApiErrorResponse(String message, List<String> errors) {
        this.message = message;
        this.errors = errors;
    }
}
