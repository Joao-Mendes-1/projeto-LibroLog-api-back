package com.joaoMendes.catalogolivro.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LivroRequest {

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
