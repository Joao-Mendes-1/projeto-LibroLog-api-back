package com.joaoMendes.catalogolivro.repository;

import com.joaoMendes.catalogolivro.entities.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro,Long> {
    // No repositório LivroRepository.java
    List<Livro> findByGenero(String genero);

}
