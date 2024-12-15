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

    // Metodo para transformar o gênero em um objeto de resposta
    public LivroResponseGenero toGeneroResponse(String genero) {
        return new LivroResponseGenero(genero);
    }

    // Metodo para mapear todos os gêneros dos livros e remover duplicatas
    public List<LivroResponseGenero> toGeneroResponseList(List<Livro> livros) {
        // Para cada livro, extrai o gênero, remove duplicatas e converte para LivroResponseGenero
        return livros.stream()
                .map(Livro::getGenero)  // Extrai o gênero de cada livro
                .distinct()  // Remove gêneros duplicados
                .map(this::toGeneroResponse)  // Converte para LivroResponseGenero
                .collect(Collectors.toList());  // Coleta os resultados em uma lista
    }

    // Metodo para converter um LivroRequest em um objeto Livro (entidade)
    public Livro toEntity(LivroRequest request) {
        Livro livro = new Livro();
        livro.setNome(request.getNome());
        livro.setAutor(request.getAutor());
        livro.setAno(request.getAno());
        livro.setGenero(request.getGenero());
        livro.setImagem(request.getImagem());
        return livro;
    }

    // Metodo para converter um Livro em um LivroDetailResponse (detalhe do livro)
    public LivroDetailResponse toDetailResponse(Livro livro) {
        // Retorna um objeto LivroDetailResponse com os dados do livro
        return new LivroDetailResponse(
                livro.getId(),
                livro.getNome(),
                livro.getAutor(),
                livro.getAno(),
                livro.getGenero(),
                livro.getImagem()
        );
    }

    // Metodo para converter um Livro em um LivroSumarioResponse (resumo do livro)
    public LivroSumarioResponse toSumarioResponse(Livro livro) {
        // Retorna um objeto LivroSumarioResponse com dados resumidos do livro
        return new LivroSumarioResponse(
                livro.getId(),
                livro.getNome(),
                livro.getImagem()
        );
    }

    // Metodo para converter uma lista de livros em uma lista de LivroSumarioResponse
    public List<LivroSumarioResponse> toSumarioResponseList(List<Livro> livros) {
        // Para cada livro da lista, converte-o para LivroSumarioResponse e retorna a lista
        return livros.stream()
                .map(this::toSumarioResponse)
                .collect(Collectors.toList());
    }
}
