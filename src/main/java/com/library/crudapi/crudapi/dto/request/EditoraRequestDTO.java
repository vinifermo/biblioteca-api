package com.library.crudapi.crudapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditoraRequestDTO {

    @NotBlank(message = "Campo nome não pode estar vazio.")
    private String nome;

    @NotNull(message = "Campo autor não pode estar vazio.")
    private EnderecoRequestDTO endereco;
}