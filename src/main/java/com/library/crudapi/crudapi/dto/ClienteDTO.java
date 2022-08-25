package com.library.crudapi.crudapi.dto;

import com.library.crudapi.crudapi.entity.Autor;
import com.library.crudapi.crudapi.entity.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {

    @NotBlank(message = "Campo nome não pode estar vazio.")
    private String nome;

    public ClienteDTO(Cliente cliente) {
        this.nome = cliente.getNome();
    }
}
