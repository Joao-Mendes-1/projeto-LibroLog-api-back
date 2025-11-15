package com.joaoMendes.catalogolivro.domain.service;

import com.joaoMendes.catalogolivro.domain.entities.Livro;
import com.joaoMendes.catalogolivro.domain.exception.LivroNotFoundException;
import com.joaoMendes.catalogolivro.domain.repository.LivroRepository;
import com.joaoMendes.catalogolivro.dto.request.LivroFiltroRequest;
import com.joaoMendes.catalogolivro.dto.request.LivroRequest;
import com.joaoMendes.catalogolivro.dto.response.LivroDetailResponse;
import com.joaoMendes.catalogolivro.dto.response.LivroResponseGenero;
import com.joaoMendes.catalogolivro.dto.response.LivroSumarioResponse;
import com.joaoMendes.catalogolivro.mapper.LivroMapper;
import com.joaoMendes.catalogolivro.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repository;

    @Autowired
    private LivroMapper mapper;

    public LivroDetailResponse create(LivroRequest request) {
        Livro livro = mapper.toEntity(request);
        Livro saved = repository.save(livro);
        return mapper.toDetailResponse(saved);
    }

    public void delete(Long id){
        Livro livro = repository.findById(id)
                .orElseThrow(() -> new LivroNotFoundException(id));
        repository.delete(livro);
    }

    public LivroDetailResponse getById(Long id) {
        Livro livro = repository.findById(id)
                .orElseThrow(() -> new LivroNotFoundException(id));
        return mapper.toDetailResponse(livro);
    }

    public List<LivroSumarioResponse> getAllSumario() {
        List<Livro> livros = repository.findAll();
        return mapper.toSumarioResponseList(livros);
    }

    public LivroDetailResponse update(Long id, LivroRequest request) {
        Livro livroExistente = repository.findById(id)
                .orElseThrow(() -> new LivroNotFoundException(id));

        Livro livroAtualizado = mapper.toEntity(request);
        livroExistente.updateFrom(livroAtualizado);

        Livro saved = repository.save(livroExistente);
        return mapper.toDetailResponse(saved);
    }

    public List<LivroResponseGenero> getGeneros() {
        List<Livro> livros = repository.findAll();
        return mapper.toGeneroResponseList(livros);
    }

    public List<LivroSumarioResponse> getSumarioByGenero(LivroFiltroRequest filtro) {
        if (filtro == null) filtro = new LivroFiltroRequest();

        String genero = StringUtils.cleanInput(filtro.getGenero());

        if (genero == null || genero.isEmpty()) {
            return mapper.toSumarioResponseList(repository.findAll());
        }

        List<Livro> livros = repository.findByGeneroContainingIgnoreCase(genero);
        return mapper.toSumarioResponseList(livros);
    }
}
