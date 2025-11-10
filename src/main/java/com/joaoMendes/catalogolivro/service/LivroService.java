package com.joaoMendes.catalogolivro.service;

import com.joaoMendes.catalogolivro.entities.Livro;
import com.joaoMendes.catalogolivro.repository.LivroRepository;
import com.joaoMendes.catalogolivro.request.LivroFiltroRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

import java.util.List;
import java.util.Optional;

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
        return repository.findAll(); // Retorna todos os livros cadastrados
    }

    public Livro update(Livro obj) {
        // Verifica se o livro já existe no banco de dados
        Optional<Livro> optionalLivro = repository.findById(obj.getId());
        if (optionalLivro.isEmpty()) {
            // Lança uma exceção se o livro não for encontrado
            throw new RuntimeException("Livro com ID " + obj.getId() + " não encontrado.");
        }
        // Obtém o livro existente e atualiza os dados
        Livro livroExistente = optionalLivro.get();
        updateLivro(livroExistente, obj);
        return repository.save(livroExistente); // Salva o livro atualizado
    }

    private void updateLivro(Livro livroExistente, Livro obj) {
        livroExistente.setAno(obj.getAno());
        livroExistente.setNome(obj.getNome());
        livroExistente.setGenero(obj.getGenero());
        livroExistente.setAutor(obj.getAutor());
        livroExistente.setImagem(obj.getImagem());
    }

    public List<String> getGeneros() {
        return repository.findAll().stream()
                .map(Livro::getGenero)
                .distinct()
                .collect(Collectors.toList());
    }

    public Livro getId(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new RuntimeException("Livro não encontrado com ID: " + id));
    }

    public List<Livro> filterByGenero(LivroFiltroRequest filtroRequest) {
        String genero = filtroRequest.getGenero();
        if (genero == null || genero.isEmpty()) {
            return repository.findAll();
        }

        return repository.findByGenero(genero);
    }
}
