package com.library.crudapi.crudapi.entity;

import com.library.crudapi.crudapi.dto.request.EnderecoRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tb_endereco", schema = "crud")
public class Endereco {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String numero;
    private String cep;
    private String cidade;
    private String estado;
    private String bairro;

    public Endereco(EnderecoRequestDTO endereco) {
        this.numero = endereco.getNumero();
        this.cep = endereco.getCep();
        this.cidade = endereco.getCidade();
        this.estado = endereco.getEstado();
        this.bairro = endereco.getBairro();
    }
}