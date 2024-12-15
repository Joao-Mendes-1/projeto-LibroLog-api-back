package com.joaoMendes.catalogoLivro.controller;

import com.joaoMendes.catalogoLivro.mapper.LivroMapper;
import com.joaoMendes.catalogoLivro.request.LivroFiltroRequest;
import com.joaoMendes.catalogoLivro.request.LivroRequest;
import com.joaoMendes.catalogoLivro.response.LivroResponseGenero;
import com.joaoMendes.catalogoLivro.response.LivroSumarioResponse;
import com.joaoMendes.catalogoLivro.response.LivroDetailResponse;
import com.joaoMendes.catalogoLivro.service.LivroService;
import com.joaoMendes.catalogoLivro.entities.Livro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/livros") // Define o caminho base para todos os endpoints deste controlador
public class LivroController {

    @Autowired
    private LivroService livroService;

    @Autowired
    private LivroMapper mapper;

    // Metodo para criar um novo livro
    @PostMapping
    public ResponseEntity<LivroDetailResponse> create(@RequestBody LivroRequest request) {
        // Converte o request DTO para a entidade Livro
        Livro livro = mapper.toEntity(request);
        // Chama o serviço para salvar o livro
        Livro savedLivro = livroService.create(livro);
        // Converte o livro salvo para o DTO de resposta
        LivroDetailResponse response = mapper.toDetailResponse(savedLivro);
        // Retorna a resposta com o status HTTP 201 (Criado)
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Metodo para deletar um livro pelo ID
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        livroService.delete(id);
        // Retorna uma resposta sem conteúdo com o status HTTP 204 (Sem Conteúdo)
        return ResponseEntity.noContent().build();
    }

    // Metodo para buscar um livro pelo ID
    @GetMapping(value = "/{id}")
    public ResponseEntity<LivroDetailResponse> getId(@PathVariable Long id) {
        Livro livro = livroService.getId(id);
        // Converte o livro encontrado para o DTO de resposta
        LivroDetailResponse response = mapper.toDetailResponse(livro);
        // Retorna o livro com o status HTTP 200 (OK)
        return ResponseEntity.ok().body(response);
    }

    // Metodo para buscar todos os livros
    @GetMapping
    public ResponseEntity<List<LivroSumarioResponse>> getAll() {
        List<Livro> livros = livroService.getAll();
        // Converte os livros para o formato de resposta esperado
        List<LivroSumarioResponse> response = mapper.toSumarioResponseList(livros);
        // Retorna a lista de livros com o status HTTP 200 (OK)
        return ResponseEntity.ok().body(response);
    }

    // Metodo para atualizar um livro existente
    @PutMapping(value = "/{id}")
    public ResponseEntity<LivroDetailResponse> update(@PathVariable Long id, @RequestBody LivroRequest request) {
        // Converte o request DTO para a entidade Livro
        Livro livro = mapper.toEntity(request);
        livro.setId(id); // Define o ID do livro a ser atualizado
        Livro updatedLivro = livroService.update(livro);
        // Converte o livro atualizado para o DTO de resposta
        LivroDetailResponse response = mapper.toDetailResponse(updatedLivro);
        // Retorna o livro atualizado com o status HTTP 200 (OK)
        return ResponseEntity.ok().body(response);
    }

    // Metodo para buscar todos os gêneros de livros
    @GetMapping(value = "/generos")
    public ResponseEntity<List<LivroResponseGenero>> getGeneros() {
        List<Livro> livros = livroService.getAll();
        // Converte os livros para uma lista de respostas com os gêneros
        List<LivroResponseGenero> response = mapper.toGeneroResponseList(livros);
        // Retorna a lista de gêneros com o status HTTP 200 (OK)
        return ResponseEntity.ok().body(response);
    }

    // Metodo para filtrar livros por gênero
    @GetMapping(value = "/filtro")
    public ResponseEntity<List<LivroSumarioResponse>> filterByGenero(@RequestParam(value = "genero", required = false) String genero) {
        // Cria o objeto de filtro e define o gênero
        LivroFiltroRequest filtroRequest = new LivroFiltroRequest();
        filtroRequest.setGenero(genero);

        // Chama o serviço para buscar livros com base no filtro
        List<Livro> livrosFiltrados = livroService.filterByGenero(filtroRequest);

        // Converte os livros filtrados para o formato de resposta
        List<LivroSumarioResponse> response = mapper.toSumarioResponseList(livrosFiltrados);

        // Retorna a lista de livros filtrados com o status HTTP 200 (OK)
        return ResponseEntity.ok().body(response);
    }
}
