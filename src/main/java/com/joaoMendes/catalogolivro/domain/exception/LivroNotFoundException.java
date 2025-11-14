package com.joaoMendes.catalogolivro.domain.exception;

public class LivroNotFoundException extends RuntimeException {

    public LivroNotFoundException(Long id) {
        super("Livro não encontrado com ID: " + id);
    }

    public LivroNotFoundException(String message) {
        super(message);
    }
}
