package com.joaoMendes.catalogoLivro.mapper;

import com.joaoMendes.catalogoLivro.entities.Livro;
import com.joaoMendes.catalogoLivro.request.LivroRequest;
import com.joaoMendes.catalogoLivro.response.LivroDetailResponse;
import com.joaoMendes.catalogoLivro.response.LivroFiltroResponse;
import com.joaoMendes.catalogoLivro.response.LivroResponseGenero;
import com.joaoMendes.catalogoLivro.response.LivroSumarioResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class LivroMapper {

    //Metodo para transformar o gênero em um objeto de resposta
    public LivroResponseGenero toGeneroResponse(String genero) {
        return new LivroResponseGenero(genero);
    }

    // Metodo para mapear todos os gêneros dos livros e remover duplicatas
    public List<LivroResponseGenero> toGeneroResponseList(List<Livro> livros) {
        return livros.stream()
                .map(Livro::getGenero)  // Extrai o gênero de cada livro
                .distinct()  // Remove gêneros duplicados
                .map(this::toGeneroResponse)  // Converte para LivroResponseGenero
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

    public List<LivroFiltroResponse> toFiltroResponse(List<Livro> livros) {
        Map<String, List<Long>> generoMap = livros.stream()
                .collect(Collectors.groupingBy(
                        Livro::getGenero,
                        Collectors.mapping(Livro::getId, Collectors.toList())
                ));

        return generoMap.entrySet().stream()
                .map(entry -> new LivroFiltroResponse(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    public List<LivroSumarioResponse> toSumarioResponseList(List<Livro> livros) {
        return livros.stream()
                .map(this::toSumarioResponse)
                .collect(Collectors.toList());
    }
}
