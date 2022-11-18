package com.library.crudapi.crudapi.service;

import com.library.crudapi.crudapi.dto.request.LivroRequestDTO;
import com.library.crudapi.crudapi.entity.Livro;

import java.util.List;
import java.util.UUID;

public interface LivroService {
    void update(UUID id, LivroRequestDTO livroRequestDTO);

    List<Livro> findAll();

    List<Livro> findByEditoraId(UUID id);

    void delete(UUID id);

    Livro findById(UUID id);

    Livro create(LivroRequestDTO livroRequestDTO);
}