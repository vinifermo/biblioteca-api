package com.library.crudapi.crudapi.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.library.crudapi.crudapi.dto.request.AutorRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "autor")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @NotEmpty(message = "Campo nome não pode estar vazio.")
    private String nome;

    @JsonIgnore
    @OneToMany(mappedBy = "autor")
    private Set<Livro> livro;

    @Embedded
    private Autorinfo autorinfo;


    public Autor(Long codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public Autor(String nome) {
        this.nome = nome;
    }

    public Autor(Long codigo) {
        this.codigo = codigo;
    }

    public Autor(AutorRequestDTO autorRequestDTO) {
        this.nome = autorRequestDTO.getNome();
        this.autorinfo = autorRequestDTO.getAutorinfo();
    }
}