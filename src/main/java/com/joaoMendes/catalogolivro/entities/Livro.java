package com.joaoMendes.catalogolivro.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")

@Entity
@Table(name = "tb_livro")
public class Livro implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("nome")
    private String nome;
    @JsonProperty("autor")
    private String autor;
    @JsonProperty("ano")
    private String ano;
    @JsonProperty("genero")
    private String genero;
    @JsonProperty("imagem")
    private String imagem;


}

