package com.library.crudapi.crudapi.service;
import com.library.crudapi.crudapi.dto.request.EditoraRequestDTO;
import com.library.crudapi.crudapi.entity.Editora;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EditoraService {

    Editora atualizar(UUID id, EditoraRequestDTO editoraRequestDTO);

    List<Editora> listar();

    Editora remover(UUID id);

    Editora buscarEditoraPeloId(UUID id);

    Editora criar(EditoraRequestDTO editoraRequestDTO);
}