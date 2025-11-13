package com.joaoMendes.catalogolivro.exceptionhandler;

import com.joaoMendes.catalogolivro.domain.exception.DomainException;
import com.joaoMendes.catalogolivro.domain.exception.LivroNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<String> capture(DomainException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(LivroNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleLivroNotFound(LivroNotFoundException ex, WebRequest request) {
        ApiErrorResponse response = new ApiErrorResponse();
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setDateTime(OffsetDateTime.now());
        response.setMessage(ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }


}
