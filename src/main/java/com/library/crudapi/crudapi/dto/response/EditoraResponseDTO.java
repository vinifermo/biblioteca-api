package com.library.crudapi.crudapi.dto.response;

import com.library.crudapi.crudapi.entity.Editora;
import com.library.crudapi.crudapi.entity.Endereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditoraResponseDTO {
    @NotBlank(message = "Campo nome não pode estar vazio.")
    private String nome;

    @NotNull(message = "Campo autor não pode estar vazio.")
    private Endereco endereco;

    public EditoraResponseDTO(Editora editora) {
        this.nome = editora.getNome();
        this.endereco = editora.getEndereco();
    }
}