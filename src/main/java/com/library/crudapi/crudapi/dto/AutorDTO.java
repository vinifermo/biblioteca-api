package com.library.crudapi.crudapi.dto;

import com.library.crudapi.crudapi.entity.Autor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AutorDTO {
    @NotBlank(message = "Campo nome não pode estar vazio.")
    private String nome;

    public AutorDTO(Autor autor) {
        this.nome = autor.getNome();
    }
}
