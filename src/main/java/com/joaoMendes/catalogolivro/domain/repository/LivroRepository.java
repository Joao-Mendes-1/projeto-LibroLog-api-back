package com.joaoMendes.catalogolivro.domain.repository;

import com.joaoMendes.catalogolivro.domain.entities.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro,Long> {

    List<Livro> findByGeneroContainingIgnoreCase(String genero);


}
