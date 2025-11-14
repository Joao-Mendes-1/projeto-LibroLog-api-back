package com.joaoMendes.catalogolivro.domain.service;

import com.joaoMendes.catalogolivro.domain.entities.Livro;
import com.joaoMendes.catalogolivro.domain.exception.LivroNotFoundException;
import com.joaoMendes.catalogolivro.domain.repository.LivroRepository;
import com.joaoMendes.catalogolivro.dto.request.LivroFiltroRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

import java.util.List;
@Service
public class LivroService {

    @Autowired
    private LivroRepository repository;

    public Livro create(Livro obj){
        return repository.save(obj);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public List<Livro> getAll(){
        return repository.findAll();
    }

    public Livro update(Livro obj) {
        Livro livroExistente = repository.findById(obj.getId())
                .orElseThrow(() -> new LivroNotFoundException(obj.getId()));

        livroExistente.updateFrom(obj);
        return repository.save(livroExistente);
    }


    public List<String> getGeneros() {
        return repository.findAll().stream()
                .map(Livro::getGenero)
                .distinct()
                .collect(Collectors.toList());
    }

    public Livro getId(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new LivroNotFoundException(id));
    }

    public List<Livro> filterByGenero(LivroFiltroRequest filtroRequest) {
        String genero = filtroRequest.getGenero();
        if (genero == null || genero.isEmpty()) {
            return repository.findAll();
        }

        return repository.findByGenero(genero);
    }

}
