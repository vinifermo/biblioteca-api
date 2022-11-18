package com.library.crudapi.crudapi.service;

import com.library.crudapi.crudapi.dto.request.EditoraRequestDTO;
import com.library.crudapi.crudapi.entity.Editora;

import java.util.List;
import java.util.UUID;

public interface EditoraService {

    void update(UUID id, EditoraRequestDTO editoraRequestDTO);

    List<Editora> findAll();

    void delete(UUID id);

    Editora findById(UUID id);

    Editora create(EditoraRequestDTO editoraRequestDTO);
}