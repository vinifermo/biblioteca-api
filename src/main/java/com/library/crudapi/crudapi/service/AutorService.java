package com.library.crudapi.crudapi.service;

import com.library.crudapi.crudapi.dto.request.AutorRequestDTO;
import com.library.crudapi.crudapi.entity.Autor;

import java.util.List;
import java.util.UUID;

public interface AutorService {

    void atualizar(UUID id, AutorRequestDTO autorRequestDTO);

    void remover(UUID id);

    List<Autor> listar();

    Autor buscarAutorPeloId(UUID id);

    Autor criar(AutorRequestDTO autorRequestDTO);
}