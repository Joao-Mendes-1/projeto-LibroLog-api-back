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
import java.util.List;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<String> capture(DomainException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(LivroNotFoundException.class)
    public ResponseEntity<Object> handleLivroNotFound(LivroNotFoundException ex) {

        ApiErrorResponse body = new ApiErrorResponse(
                ex.getMessage(),
                List.of(HttpStatus.NOT_FOUND.name())  // sempre ONE error
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(body);
    }





}
