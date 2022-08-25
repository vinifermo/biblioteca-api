package com.library.crudapi.crudapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class Autorinfo {

    private LocalDate nascimento;

    private String cpf;

    private String sexo;
}
