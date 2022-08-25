package com.library.crudapi.crudapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "livro")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private Long codigo;

    @NotEmpty(message = "Campo nome não pode estar vazio.")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "codigo_autor")
    private Autor autor;

    @ManyToOne
    @JoinColumn(name = "codigo_editora")
    private Editora editora;
}


