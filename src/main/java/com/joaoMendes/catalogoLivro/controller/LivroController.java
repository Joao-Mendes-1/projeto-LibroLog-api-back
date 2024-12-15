package com.joaoMendes.catalogoLivro.controller;

import com.joaoMendes.catalogoLivro.mapper.LivroMapper;
import com.joaoMendes.catalogoLivro.request.LivroFiltroRequest;
import com.joaoMendes.catalogoLivro.request.LivroRequest;
import com.joaoMendes.catalogoLivro.response.LivroFiltroResponse;
import com.joaoMendes.catalogoLivro.response.LivroResponseGenero;
import com.joaoMendes.catalogoLivro.response.LivroSumarioResponse;
import com.joaoMendes.catalogoLivro.response.LivroDetailResponse;
import com.joaoMendes.catalogoLivro.service.LivroService;
import com.joaoMendes.catalogoLivro.entities.Livro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/livros")
public class LivroController {

    @Autowired
    private LivroService service;

    @Autowired
    private LivroMapper mapper;

    @PostMapping
    public ResponseEntity<LivroDetailResponse> create(@RequestBody LivroRequest request) {
        Livro livro = mapper.toEntity(request);
        Livro savedLivro = service.create(livro);
        LivroDetailResponse response = mapper.toDetailResponse(savedLivro);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<LivroDetailResponse> getId(@PathVariable Long id) {
        Livro livro = service.getId(id);
        LivroDetailResponse response = mapper.toDetailResponse(livro);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<List<LivroSumarioResponse>> getAll() {
        List<Livro> livros = service.getAll();
        List<LivroSumarioResponse> response = mapper.toSumarioResponseList(livros);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<LivroDetailResponse> update(@PathVariable Long id, @RequestBody LivroRequest request) {
        Livro livro = mapper.toEntity(request);
        livro.setId(id);
        Livro updatedLivro = service.update(livro);
        LivroDetailResponse response = mapper.toDetailResponse(updatedLivro);
        return ResponseEntity.ok().body(response);
    }


    @GetMapping(value = "/generos")
    public ResponseEntity<List<LivroResponseGenero>> getGeneros() {
        List<Livro> livros = service.getAll(); // Buscar todos os livros
        List<LivroResponseGenero> response = mapper.toGeneroResponseList(livros); // Extrair os gêneros
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = "/filtro")
    public ResponseEntity<List<LivroSumarioResponse>> filterByGenero(@RequestParam(value = "genero", required = false) String genero) {
        LivroFiltroRequest filtroRequest = new LivroFiltroRequest();
        filtroRequest.setGenero(genero);

        // Chama o serviço para buscar livros com base no filtro
        List<Livro> livrosFiltrados = service.filterByGenero(filtroRequest);

        // Mapeia os livros filtrados para a resposta desejada
        List<LivroSumarioResponse> response = mapper.toSumarioResponseList(livrosFiltrados);

        return ResponseEntity.ok().body(response);
    }


}
