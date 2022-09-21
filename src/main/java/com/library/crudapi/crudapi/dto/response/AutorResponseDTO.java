package com.library.crudapi.crudapi.dto.response;

import com.library.crudapi.crudapi.entity.Autor;
import com.library.crudapi.crudapi.entity.Autorinfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AutorResponseDTO {
    @NotBlank(message = "Campo nome não pode estar vazio.")
    private String nome;

    private Autorinfo autorinfo;

    public AutorResponseDTO(Autor autor) {
        this.nome = autor.getNome();
        this.autorinfo = autor.getAutorinfo();
    }
}