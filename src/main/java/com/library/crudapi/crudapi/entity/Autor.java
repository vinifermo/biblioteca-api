package com.library.crudapi.crudapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.library.crudapi.crudapi.dto.request.AutorRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_autor", schema = "crud")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String nome;

    @JsonIgnore
    @OneToMany(mappedBy = "autor")
    private Set<Livro> livro;

    @Embedded
    private Autorinfo autorinfo;

    public Autor(AutorRequestDTO autorRequestDTO) {
        this.nome = autorRequestDTO.getNome();
        this.autorinfo = autorRequestDTO.getAutorinfo();
    }
}