package com.library.crudapi.crudapi.dto;

import com.library.crudapi.crudapi.entity.Autor;
import com.library.crudapi.crudapi.entity.Editora;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LivroDTO {

    @NotNull(message = "Campo autor não pode estar vazio.")
    private Autor autor;

    @NotNull(message = "Campo editora não pode estar vazio.")
    private Editora editora;

    @NotBlank(message = "Campo nome não pode estar vazio.")
    private String nome;

    @NotBlank(message = "Campo genero não pode estar vazio.")
    private String genero;

    private String paginas;
}
