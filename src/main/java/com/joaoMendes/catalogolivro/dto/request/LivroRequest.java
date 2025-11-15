package com.joaoMendes.catalogolivro.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LivroRequest {

    @NotBlank(message = "O nome do livro é obrigatório.")
    @Size(max = 60)
    private String nome;

    @NotBlank(message = "O nome do autor é obrigatório.")
    @Size(max = 60)
    private String autor;

    @Min(value = 1000, message = "O ano deve ser no mínimo 1000.")
    @Max(value = 2100, message = "O ano deve ser no máximo 2100.")
    private Integer ano;

    @NotBlank(message = "O gênero do livro é obrigatório.")
    @Size(max = 60)
    private String genero;

    @NotBlank(message = "A URL da imagem é obrigatória.")
    @Size(max = 255)
    private String imagem;

}
