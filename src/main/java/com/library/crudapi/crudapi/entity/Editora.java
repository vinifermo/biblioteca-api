package com.library.crudapi.crudapi.entity;

import com.library.crudapi.crudapi.dto.request.EditoraRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Endereco endereco;

    public Editora(EditoraRequestDTO editoraRequestDTO) {
        this.nome = editoraRequestDTO.getNome();
        this.endereco = new Endereco(editoraRequestDTO.getEndereco());
    }
}