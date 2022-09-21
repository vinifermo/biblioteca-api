package com.library.crudapi.crudapi.entity;
import com.library.crudapi.crudapi.dto.request.ClienteRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_cliente", schema = "crud")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String nome;

    @Embedded
    private Clienteinfo clienteinfo;

    @OneToOne(cascade = CascadeType.ALL)
    private Endereco endereco;

    public Cliente(ClienteRequestDTO clienteRequestDTO) {
        this.nome = clienteRequestDTO.getNome();
        this.endereco = new Endereco(clienteRequestDTO.getEndereco());
        this.clienteinfo = clienteRequestDTO.getClienteinfo();
    }
}