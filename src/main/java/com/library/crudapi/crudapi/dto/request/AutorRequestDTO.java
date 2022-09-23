package com.library.crudapi.crudapi.dto.request;

import com.library.crudapi.crudapi.entity.Autorinfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AutorRequestDTO {

    @NotBlank(message = "Campo nome não pode estar vazio.")
    private String nome;

    @NotNull(message = "Campo autorinfo não pode estar vazio.")
    private Autorinfo autorinfo;
}