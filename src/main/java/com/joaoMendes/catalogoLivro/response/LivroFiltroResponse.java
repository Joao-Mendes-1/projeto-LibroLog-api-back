package com.joaoMendes.catalogoLivro.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LivroFiltroResponse {

    @JsonProperty("genero")
    private String genero;

    @JsonProperty("id")
    private List<Long> id;
}
