package com.joaoMendes.catalogoLivro.service;

import com.joaoMendes.catalogoLivro.entities.Livro;
import com.joaoMendes.catalogoLivro.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return repository.findAll();
    }

    public Livro update(Livro obj) {
        Optional<Livro> optionalLivro = repository.findById(obj.getId());
        if (optionalLivro.isEmpty()) {
            throw new RuntimeException("Livro com ID " + obj.getId() + " não encontrado.");
        }
        Livro livroExistente = optionalLivro.get();
        updateLivro(livroExistente, obj);
        return repository.save(livroExistente);
    }

    private void updateLivro(Livro livroExistente, Livro obj) {
        livroExistente.setAno(obj.getAno());
        livroExistente.setNome(obj.getNome());
        livroExistente.setGenero(obj.getGenero());
        livroExistente.setAutor(obj.getAutor());
        livroExistente.setImagem(obj.getImagem());
    }

    public Livro getId(Long id) {
    return repository.findById(id).orElseThrow(() -> new RuntimeException("Livro não encontrado com ID: " + id));
}


}
