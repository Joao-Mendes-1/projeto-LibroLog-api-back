package com.joaoMendes.catalogoLivro.repository;

import com.joaoMendes.catalogoLivro.entities.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro,Long> {
    // No repositório LivroRepository.java
    List<Livro> findByGenero(String genero);

}
