package com.library.crudapi.crudapi.dto.request;

import com.library.crudapi.crudapi.entity.Clienteinfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequestDTO {

    @NotNull(message = "Campo nome não pode estar vazio.")
    private String nome;

    @NotNull(message = "Campo autor não pode estar vazio.")
    private EnderecoRequestDTO endereco;

    @NotNull(message = "Campo autorinfo não pode estar vazio.")
    private Clienteinfo clienteinfo;
}