package com.library.crudapi.crudapi.dto.response;

import com.library.crudapi.crudapi.entity.Cliente;
import com.library.crudapi.crudapi.entity.Clienteinfo;
import com.library.crudapi.crudapi.entity.Endereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteResponseDTO {
    @NotBlank(message = "Campo nome não pode estar vazio.")
    private String nome;

    private Clienteinfo clienteinfo;
    private Endereco endereco;

    public ClienteResponseDTO(Cliente cliente) {
        this.nome = cliente.getNome();
        this.clienteinfo = cliente.getClienteinfo();
        this.endereco = cliente.getEndereco();
    }
}