package com.library.crudapi.crudapi.dto.response;

import com.library.crudapi.crudapi.entity.Livro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LivroResponseDTO {
    @NotNull(message = "Campo autor não pode estar vazio.")
    private String autorId;

    @NotNull(message = "Campo editora não pode estar vazio.")
    private String editoraId;

    @NotBlank(message = "Campo nome não pode estar vazio.")
    private String nome;

    @NotBlank(message = "Campo genero não pode estar vazio.")
    private String genero;

    public LivroResponseDTO(Livro livro) {
        this.autorId = livro.getAutor().getId().toString();
        this.editoraId = livro.getEditora().getId().toString();
        this.nome = livro.getNome();
        this.genero = livro.getGenero();
    }
}