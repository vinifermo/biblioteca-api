package com.library.crudapi.crudapi.service;

import com.library.crudapi.crudapi.dto.request.EditoraRequestDTO;
import com.library.crudapi.crudapi.entity.Editora;

import java.util.List;
import java.util.UUID;

public interface EditoraService {

    void atualizar(UUID id, EditoraRequestDTO editoraRequestDTO);

    List<Editora> listar();

     void remover(UUID id);

    Editora buscarEditoraPeloId(UUID id);

    Editora criar(EditoraRequestDTO editoraRequestDTO);
}