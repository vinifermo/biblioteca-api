package com.library.crudapi.crudapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoResponseDTO {
    @NotBlank(message = "Campo nome não pode estar vazio.")
    private String numero;
    @NotBlank(message = "Campo nome não pode estar vazio.")
    private String cep;
    @NotBlank(message = "Campo nome não pode estar vazio.")
    private String cidade;
    @NotBlank(message = "Campo nome não pode estar vazio.")
    private String estado;
    @NotBlank(message = "Campo nome não pode estar vazio.")
    private String bairro;
}