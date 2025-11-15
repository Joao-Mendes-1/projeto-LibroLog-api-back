package com.joaoMendes.catalogolivro.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "tb_livro")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Livro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String autor;
    private Integer ano;
    private String genero;
    private String imagem;

    public void updateFrom(Livro source) {
        this.nome = source.getNome();
        this.autor = source.getAutor();
        this.ano = source.getAno();
        this.genero = source.getGenero();
        this.imagem = source.getImagem();
    }
}
