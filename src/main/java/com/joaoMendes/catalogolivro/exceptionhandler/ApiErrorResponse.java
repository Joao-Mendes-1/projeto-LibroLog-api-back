package com.joaoMendes.catalogolivro.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ApiErrorResponse {

    private String mensagem;
    private List<String> erros;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime timestamp;

    public ApiErrorResponse(String message, List<String> errors, LocalDateTime timestamp) {
        this.mensagem = mensagem;
        this.erros = errors;
        this.timestamp = timestamp;
    }
}
