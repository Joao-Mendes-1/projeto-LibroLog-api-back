package com.joaoMendes.catalogolivro.controller;

import com.joaoMendes.catalogolivro.mapper.LivroMapper;
import com.joaoMendes.catalogolivro.request.LivroFiltroRequest;
import com.joaoMendes.catalogolivro.request.LivroRequest;
import com.joaoMendes.catalogolivro.response.LivroResponseGenero;
import com.joaoMendes.catalogolivro.response.LivroSumarioResponse;
import com.joaoMendes.catalogolivro.response.LivroDetailResponse;
import com.joaoMendes.catalogolivro.service.LivroService;
import com.joaoMendes.catalogolivro.entities.Livro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @Autowired
    private LivroMapper mapper;

    @PostMapping
    public ResponseEntity<LivroDetailResponse> create(@RequestBody LivroRequest request) {

        Livro livro = mapper.toEntity(request);

        Livro savedLivro = livroService.create(livro);

        LivroDetailResponse response = mapper.toDetailResponse(savedLivro);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        livroService.delete(id);

        return ResponseEntity.noContent().build();
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<LivroDetailResponse> getId(@PathVariable Long id) {
        Livro livro = livroService.getId(id);

        LivroDetailResponse response = mapper.toDetailResponse(livro);

        return ResponseEntity.ok().body(response);
    }


    @GetMapping
    public ResponseEntity<List<LivroSumarioResponse>> getAll() {
        List<Livro> livros = livroService.getAll();

        List<LivroSumarioResponse> response = mapper.toSumarioResponseList(livros);

        return ResponseEntity.ok().body(response);
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<LivroDetailResponse> update(@PathVariable Long id, @RequestBody LivroRequest request) {

        Livro livro = mapper.toEntity(request);
        livro.setId(id);
        Livro updatedLivro = livroService.update(livro);

        LivroDetailResponse response = mapper.toDetailResponse(updatedLivro);

        return ResponseEntity.ok().body(response);
    }


    @GetMapping(value = "/generos")
    public ResponseEntity<List<LivroResponseGenero>> getGeneros() {
        List<Livro> livros = livroService.getAll();

        List<LivroResponseGenero> response = mapper.toGeneroResponseList(livros);

        return ResponseEntity.ok().body(response);
    }


    @GetMapping(value = "/filtro")
    public ResponseEntity<List<LivroSumarioResponse>> filterByGenero(@RequestParam(value = "genero", required = false) String genero) {

        LivroFiltroRequest filtroRequest = new LivroFiltroRequest();
        filtroRequest.setGenero(genero);


        List<Livro> livrosFiltrados = livroService.filterByGenero(filtroRequest);


        List<LivroSumarioResponse> response = mapper.toSumarioResponseList(livrosFiltrados);


        return ResponseEntity.ok().body(response);
    }
}
