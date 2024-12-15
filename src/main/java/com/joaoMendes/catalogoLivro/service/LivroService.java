package com.joaoMendes.catalogoLivro.service;

import com.joaoMendes.catalogoLivro.entities.Livro;
import com.joaoMendes.catalogoLivro.repository.LivroRepository;
import com.joaoMendes.catalogoLivro.request.LivroFiltroRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repository;

    // Metodo para criar um novo livro no banco de dados
    public Livro create(Livro obj){
        return repository.save(obj); // Salva o livro no banco de dados e retorna o objeto salvo
    }

    // Metodo para deletar um livro usando o ID
    public void delete(Long id){
        repository.deleteById(id); // Deleta o livro com o ID fornecido
    }

    // Metodo para obter todos os livros
    public List<Livro> getAll(){
        return repository.findAll(); // Retorna todos os livros cadastrados
    }

    // Metodo para atualizar um livro existente
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

    // Metodo auxiliar que atualiza os dados do livro existente com os dados do novo livro
    private void updateLivro(Livro livroExistente, Livro obj) {
        livroExistente.setAno(obj.getAno());
        livroExistente.setNome(obj.getNome());
        livroExistente.setGenero(obj.getGenero());
        livroExistente.setAutor(obj.getAutor());
        livroExistente.setImagem(obj.getImagem());
    }

    // Metodo para obter uma lista de todos os gêneros distintos dos livros
    public List<String> getGeneros() {
        return repository.findAll().stream()
                .map(Livro::getGenero) // Extrai o gênero de cada livro
                .distinct() // Remove duplicatas
                .collect(Collectors.toList()); // Coleta os gêneros em uma lista
    }

    // Metodo para obter um livro por seu ID. Se não encontrado, lança uma exceção
    public Livro getId(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new RuntimeException("Livro não encontrado com ID: " + id));
    }

    // Metodo para filtrar livros por gênero com base no filtro fornecido
    public List<Livro> filterByGenero(LivroFiltroRequest filtroRequest) {
        String genero = filtroRequest.getGenero();
        // Se o filtro de gênero não for fornecido ou estiver vazio, retorna todos os livros
        if (genero == null || genero.isEmpty()) {
            return repository.findAll();
        }
        // Caso contrário, filtra os livros pelo gênero fornecido
        return repository.findByGenero(genero);
    }
}
