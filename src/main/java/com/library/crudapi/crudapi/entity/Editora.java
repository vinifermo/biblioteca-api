package com.library.crudapi.crudapi.entity;
import com.library.crudapi.crudapi.dto.request.EditoraRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "editora")
public class Editora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @NotEmpty(message = "Campo nome não pode estar vazio.")
    private String nome;

    @Embedded
    private Endereco endereco;

    public Editora(EditoraRequestDTO editoraRequestDTO) {
        this.nome = editoraRequestDTO.getNome();
    }
}