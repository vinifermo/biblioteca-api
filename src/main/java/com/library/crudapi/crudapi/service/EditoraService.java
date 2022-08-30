package com.library.crudapi.crudapi.service;
import com.library.crudapi.crudapi.dto.request.EditoraRequestDTO;
import com.library.crudapi.crudapi.entity.Editora;
import java.util.List;
import java.util.Optional;

public interface EditoraService {

    Editora atualizar(Long codigo, EditoraRequestDTO editoraRequestDTO);

    List<Editora> listar();

    Editora remover(Long codigo);

    Optional<Editora> buscarPeloCodigo(Long codigo);

    Editora criar(EditoraRequestDTO editoraRequestDTO);
}