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


    @NotEmpty(message = "Campo genero não pode estar vazio.")
    private String genero;

    private String paginas;

    @ManyToOne
    @JoinColumn(name = "codigo_autor")
    private Autor autor;

    @ManyToOne
    @JoinColumn(name = "codigo_editora")
    private Editora editora;

    public Livro(String nome, String genero, String paginas) {
        this.nome = nome;
        this.genero = genero;
        this.paginas = paginas;
    }
    public Livro(Autor autor, Editora editora, String name, String genero, String paginas) {
        this.autor = autor;
        this.editora = editora;
        this.nome = name;
        this.genero = genero;
        this.paginas = paginas;
    }

}


