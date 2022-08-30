package com.library.crudapi.crudapi.dto.response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AutorResponseDTO {
    @NotBlank(message = "Campo nome não pode estar vazio.")
    private String nome;
}