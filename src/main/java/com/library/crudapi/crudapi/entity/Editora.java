package com.library.crudapi.crudapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.library.crudapi.crudapi.dto.request.EditoraRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_editora", schema = "crud")
public class Editora {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String nome;

    @JsonIgnore
    @OneToMany(mappedBy = "editora", fetch = FetchType.LAZY)
    private Set<Livro> livro;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Endereco endereco;

    public Editora(EditoraRequestDTO editoraRequestDTO) {
        this.nome = editoraRequestDTO.getNome();
        this.endereco = new Endereco(editoraRequestDTO.getEndereco());
    }
}