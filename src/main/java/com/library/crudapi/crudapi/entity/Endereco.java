package com.library.crudapi.crudapi.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Embeddable;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class Endereco {
    private String numero;
    private String bairro;
    private String cep;
    private String cidade;
    private String estado;
}