package com.joaoMendes.catalogolivro.domain.service;

import com.joaoMendes.catalogolivro.domain.entities.Livro;
import com.joaoMendes.catalogolivro.domain.exception.LivroNotFoundException;
import com.joaoMendes.catalogolivro.domain.repository.LivroRepository;
import com.joaoMendes.catalogolivro.dto.request.LivroFiltroRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LivroServiceTest {

    @Mock
    private LivroRepository repository;

    @InjectMocks
    private LivroService service;

    @Test
    void deveAtualizarLivroQuandoExiste() {

        Livro request = new Livro(1L, "Antigo", "Autor A", "2001", "Ficção", "imagem1.jpg");
        Livro updatedBook = new Livro(1L, "Novo", "Autor B", "2023", "Drama", "imagem2.jpg");

        when(repository.findById(1L)).thenReturn(Optional.of(request));
        when(repository.save(any(Livro.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Livro result = service.update(updatedBook);

        assertEquals("Novo", result.getNome());
        assertEquals("Autor B", result.getAutor());
        assertEquals("Drama", result.getGenero());
        assertEquals("2023", result.getAno());
        assertEquals("imagem2.jpg", result.getImagem());

        verify(repository).findById(1L);
        verify(repository).save(request);
    }

    @Test
    void deveLancarExcecaoQuandoLivroNaoExistir() {

        Livro nonExistentBook = new Livro(9929L, "Fantasma", "Ninguém", "2000", "Mistério", "img.jpg");
        when(repository.findById(9929L)).thenReturn(Optional.empty());

        assertThrows(LivroNotFoundException.class, () -> service.update(nonExistentBook));

        verify(repository).findById(9929L);
        verify(repository, never()).save(any());
    }



    @Test
    void deveRetornarTodosOsLivrosQuandoGeneroEstiverVazio() {

        LivroFiltroRequest request = new LivroFiltroRequest();
        request.setGenero(""); // gênero vazio

        List<Livro> allBooks = List.of(
                new Livro(1L, "Livro A", "Autor A", "2001", "Ficção", "img1.jpg"),
                new Livro(2L, "Livro B", "Autor B", "2010", "Terror", "img2.jpg")
        );

        when(repository.findAll()).thenReturn(allBooks);

        List<Livro> result = service.filterByGenero(request);

        assertEquals(2, result.size());
        assertEquals(allBooks, result);

        verify(repository).findAll();
        verify(repository, never()).findByGenero(anyString());
    }

    @Test
    void deveRetornarApenasOsLivrosDoGeneroInformado() {

        LivroFiltroRequest request = new LivroFiltroRequest();
        request.setGenero("Drama");

        List<Livro> filteredBooks = List.of(
                new Livro(3L, "Livro C", "Autor C", "2015", "Drama", "img3.jpg")
        );

        when(repository.findByGenero("Drama")).thenReturn(filteredBooks);

        List<Livro> result = service.filterByGenero(request);

        assertEquals(1, result.size());
        assertEquals("Drama", result.get(0).getGenero());

        verify(repository).findByGenero("Drama");
        verify(repository, never()).findAll();
    }
}

