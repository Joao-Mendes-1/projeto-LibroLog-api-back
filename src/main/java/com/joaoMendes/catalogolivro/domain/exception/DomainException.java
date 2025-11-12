package com.joaoMendes.catalogolivro.domain.exception;

public class DomainException extends RuntimeException {

    public DomainException(String message) {
        super(message);
    }
    public class ValidationException extends DomainException {
        public ValidationException(String message) {
            super(message);
        }
    }
}
