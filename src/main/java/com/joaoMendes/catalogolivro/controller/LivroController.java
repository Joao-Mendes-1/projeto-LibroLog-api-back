package com.joaoMendes.catalogolivro.controller;

import com.joaoMendes.catalogolivro.dto.request.LivroFiltroRequest;
import com.joaoMendes.catalogolivro.dto.request.LivroRequest;
import com.joaoMendes.catalogolivro.dto.response.LivroResponseGenero;
import com.joaoMendes.catalogolivro.dto.response.LivroSumarioResponse;
import com.joaoMendes.catalogolivro.dto.response.LivroDetailResponse;
import com.joaoMendes.catalogolivro.domain.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @PostMapping
    public ResponseEntity<LivroDetailResponse> create(@Valid @RequestBody LivroRequest request) {

        LivroDetailResponse response = livroService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        livroService.delete(id);

        return ResponseEntity.noContent().build();
    }


    @GetMapping("{id}")
    public ResponseEntity<LivroDetailResponse> getId(@PathVariable Long id) {

        LivroDetailResponse response = livroService.getById(id);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<LivroSumarioResponse>> getAll() {
        List<LivroSumarioResponse> response = livroService.getAllSumario();
        return ResponseEntity.ok(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<LivroDetailResponse> update(@PathVariable Long id, @RequestBody @Valid LivroRequest request) {

        LivroDetailResponse response = livroService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("generos")
    public ResponseEntity<List<LivroResponseGenero>> getGeneros() {
        List<LivroResponseGenero> response = livroService.getGeneros();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/filtro")
    public ResponseEntity<List<LivroSumarioResponse>> filterByGenero(@RequestParam(value = "genero", required = false) String genero) {

        LivroFiltroRequest filtro = new LivroFiltroRequest();
        filtro.setGenero(genero);

        List<LivroSumarioResponse> response = livroService.getSumarioByGenero(filtro);
        return ResponseEntity.ok(response);
    }

}
