package com.joaoMendes.catalogoLivro.controller;

import com.joaoMendes.catalogoLivro.request.LivroRequest;
import com.joaoMendes.catalogoLivro.response.LivroSumarioResponse;
import com.joaoMendes.catalogoLivro.response.LivroDetailResponse;
import com.joaoMendes.catalogoLivro.service.LivroService;
import com.joaoMendes.catalogoLivro.entities.Livro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/livros")
public class LivroController {

    @Autowired
    private LivroService service;

    @PostMapping
    public ResponseEntity<LivroDetailResponse> create(@RequestBody LivroRequest request) {
        Livro livro = new Livro();
        livro.setNome(request.getNome());
        livro.setAutor(request.getAutor());
        livro.setAno(request.getAno());
        livro.setGenero(request.getGenero());
        livro.setImagem(request.getImagem());

        Livro savedLivro = service.create(livro);
        LivroDetailResponse response = new LivroDetailResponse(savedLivro.getId(), savedLivro.getNome(), savedLivro.getAutor(), savedLivro.getAno(), savedLivro.getGenero(), savedLivro.getImagem());

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
        LivroDetailResponse response = new LivroDetailResponse(livro.getId(), livro.getNome(), livro.getAutor(), livro.getAno(), livro.getGenero(), livro.getImagem());
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<List<LivroSumarioResponse>> getAll() {
        List<Livro> livros = service.getAll();
        List<LivroSumarioResponse> response = livros.stream()
                .map(livro -> new LivroSumarioResponse(livro.getId(), livro.getNome(), livro.getImagem()))  // Incluindo id, nome e imagem
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(response);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<LivroDetailResponse> update(@PathVariable Long id, @RequestBody LivroRequest request) {
        Livro livro = new Livro();
        livro.setId(id);
        livro.setNome(request.getNome());
        livro.setAutor(request.getAutor());
        livro.setAno(request.getAno());
        livro.setGenero(request.getGenero());
        livro.setImagem(request.getImagem());

        Livro updatedLivro = service.update(livro);
        LivroDetailResponse response = new LivroDetailResponse(updatedLivro.getId(), updatedLivro.getNome(), updatedLivro.getAutor(), updatedLivro.getAno(), updatedLivro.getGenero(), updatedLivro.getImagem());

        return ResponseEntity.ok().body(response);
    }
}
