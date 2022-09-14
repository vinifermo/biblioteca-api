package com.library.crudapi.crudapi.entity;
import com.library.crudapi.crudapi.dto.request.LivroRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_livro", schema = "crud")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private UUID id;

    @NotEmpty(message = "Campo nome não pode estar vazio.")
    private String nome;


    @NotEmpty(message = "Campo genero não pode estar vazio.")
    private String genero;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;

    @ManyToOne
    @JoinColumn(name = "editora_id")
    private Editora editora;

    public Livro(String nome, String genero) {
        this.nome = nome;
        this.genero = genero;

    }
    public Livro(LivroRequestDTO livroRequestDTO) {
        this.autor = livroRequestDTO.getAutor();
        this.editora = livroRequestDTO.getEditora();
        this.nome = livroRequestDTO.getNome();
        this.genero = livroRequestDTO.getGenero();
    }
}