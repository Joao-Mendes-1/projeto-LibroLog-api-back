package com.joaoMendes.catalogolivro.mapper;

import com.joaoMendes.catalogolivro.entities.Livro;
import com.joaoMendes.catalogolivro.request.LivroRequest;
import com.joaoMendes.catalogolivro.response.LivroDetailResponse;
import com.joaoMendes.catalogolivro.response.LivroResponseGenero;
import com.joaoMendes.catalogolivro.response.LivroSumarioResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LivroMapper {

    public LivroResponseGenero toGeneroResponse(String genero) {
        return new LivroResponseGenero(genero);
    }

    public List<LivroResponseGenero> toGeneroResponseList(List<Livro> livros) {

        return livros.stream()
                .map(Livro::getGenero)
                .distinct()
                .map(this::toGeneroResponse)
                .collect(Collectors.toList());
    }


    public Livro toEntity(LivroRequest request) {
        Livro livro = new Livro();
        livro.setNome(request.getNome());
        livro.setAutor(request.getAutor());
        livro.setAno(request.getAno());
        livro.setGenero(request.getGenero());
        livro.setImagem(request.getImagem());
        return livro;
    }


    public LivroDetailResponse toDetailResponse(Livro livro) {

        return new LivroDetailResponse(
                livro.getId(),
                livro.getNome(),
                livro.getAutor(),
                livro.getAno(),
                livro.getGenero(),
                livro.getImagem()
        );
    }


    public LivroSumarioResponse toSumarioResponse(Livro livro) {

        return new LivroSumarioResponse(
                livro.getId(),
                livro.getNome(),
                livro.getImagem()
        );
    }


    public List<LivroSumarioResponse> toSumarioResponseList(List<Livro> livros) {

        return livros.stream()
                .map(this::toSumarioResponse)
                .collect(Collectors.toList());
    }
}
