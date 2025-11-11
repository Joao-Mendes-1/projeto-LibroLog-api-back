package com.joaoMendes.catalogolivro.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @NotBlank
    @Size(max = 60)
    @JsonProperty("nome")
    private String nome;

    @NotBlank
    @Size(max = 60)
    @JsonProperty("autor")
    private String autor;

    @JsonProperty("ano")
    private String ano;

    @NotBlank
    @Size(max = 60)
    @JsonProperty("genero")
    private String genero;

    @NotBlank
    @Size(max = 255)
    @JsonProperty("imagem")
    private String imagem;

}

