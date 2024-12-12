package com.joaoMendes.catalogoLivro.repository;

import com.joaoMendes.catalogoLivro.entities.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro,Long> {
}
