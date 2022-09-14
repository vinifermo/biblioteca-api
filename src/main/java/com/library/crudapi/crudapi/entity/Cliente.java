package com.library.crudapi.crudapi.entity;
import com.library.crudapi.crudapi.dto.request.ClienteRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_cliente", schema = "crud")
public class Cliente {
    @Id
    @ColumnDefault("random_uuid()")
    private UUID id;
    @NotEmpty(message = "Campo nome não pode estar vazio")
    private String nome;

    @Embedded
    private Clienteinfo clienteinfo;

    @OneToOne(fetch = FetchType.LAZY)
    private Endereco endereco;

    public Cliente(UUID id, String name) {
        this.id = id;
        this.nome = name;
    }

    public Cliente(String nome) {
        this.nome = nome;
    }

    public Cliente(UUID id) {
        this.id = id;
    }

    public Cliente(ClienteRequestDTO clienteRequestDTO) {
        this.nome = clienteRequestDTO.getNome();

    }
}