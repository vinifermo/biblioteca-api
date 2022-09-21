package com.library.crudapi.crudapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoResponseDTO {
    @NotNull(message = "Campo nome não pode estar vazio.")
    private String numero;
    @NotNull(message = "Campo nome não pode estar vazio.")
    private String cep;
    @NotNull(message = "Campo nome não pode estar vazio.")
    private String cidade;
    @NotNull(message = "Campo nome não pode estar vazio.")
    private String estado;
    @NotNull(message = "Campo nome não pode estar vazio.")
    private String bairro;
}
