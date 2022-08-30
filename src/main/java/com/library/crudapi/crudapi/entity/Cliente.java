package com.library.crudapi.crudapi.entity;
import com.library.crudapi.crudapi.dto.request.ClienteRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    @NotEmpty(message = "Campo nome não pode estar vazio")
    private String nome;

    @NotEmpty(message = "Campo ativo não pode estar vazio")
    private boolean ativo;

    @Embedded
    private Clienteinfo clienteinfo;

    @Embedded
    private Endereco endereco;

    public Cliente(Long codigo, String name) {
        this.codigo = codigo;
        this.nome = name;
    }

    public Cliente(String nome) {
        this.nome = nome;
    }

    public Cliente(Long codigo) {
        this.codigo = codigo;
    }

    public Cliente(ClienteRequestDTO clienteRequestDTO) {
        this.nome = clienteRequestDTO.getNome();

    }
}