package com.library.crudapi.crudapi.service;

import com.library.crudapi.crudapi.dto.request.AutorRequestDTO;
import com.library.crudapi.crudapi.entity.Autor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface AutorService {

    void atualizar(UUID id, AutorRequestDTO autorRequestDTO);

    void remover(UUID id);

    Page<Autor> findByPage(String filter, Pageable pageable);

    Autor buscarAutorPeloId(UUID id);

    Autor criar(AutorRequestDTO autorRequestDTO);
}