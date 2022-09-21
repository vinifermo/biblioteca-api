package com.library.crudapi.crudapi.exceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Erro {
    private String mensagemUsuario;
    private String mensagemDesenvolvedor;
}