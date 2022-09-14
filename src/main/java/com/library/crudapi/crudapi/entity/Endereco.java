package com.library.crudapi.crudapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tb_endereco", schema = "crud")
public class Endereco {
    @Id
    @EqualsAndHashCode.Include
    private UUID id;
    private String numero;
    private String cep;
    private String cidade;
    private String estado;
    private String bairro;
}