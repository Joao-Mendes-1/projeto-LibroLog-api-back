package com.joaoMendes.catalogoLivro.mapper;

import com.joaoMendes.catalogoLivro.entities.Livro;
import com.joaoMendes.catalogoLivro.request.LivroRequest;
import com.joaoMendes.catalogoLivro.response.LivroResponse;
import org.springframework.stereotype.Component;

@Component
public class LivroMapper {

    public Livro toEntity(LivroRequest request) {
        Livro livro = new Livro();
        livro.setNome(request.getNome());
        livro.setAutor(request.getAutor());
        livro.setAno(request.getAno());
        livro.setGenero(request.getGenero());
        livro.setImagem(request.getImagem());
        return livro;
    }

    public LivroResponse toResponse(Livro livro) {
        return new LivroResponse(livro.getNome(), livro.getAutor(), livro.getAno(), livro.getGenero(), livro.getImagem());
    }
}
