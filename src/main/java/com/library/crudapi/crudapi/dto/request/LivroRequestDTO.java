package com.library.crudapi.crudapi.dto.request;
import com.library.crudapi.crudapi.entity.Autor;
import com.library.crudapi.crudapi.entity.Editora;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LivroRequestDTO {
    @NotNull(message = "Campo autor não pode estar vazio.")
    private Autor autor;

    @NotNull(message = "Campo editora não pode estar vazio.")
    private Editora editora;

    @NotNull(message = "Campo nome não pode estar vazio.")
    private String nome;

    @NotNull(message = "Campo genero não pode estar vazio.")
    private String genero;

    private String paginas;

}
