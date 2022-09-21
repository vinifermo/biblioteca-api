package com.library.crudapi.crudapi.service;
import com.library.crudapi.crudapi.dto.request.LivroRequestDTO;
import com.library.crudapi.crudapi.entity.Livro;
import java.util.List;
import java.util.UUID;

public interface LivroService {
    void atualizar(UUID id, LivroRequestDTO livroRequestDTO);

    List<Livro> listar();

    Livro remover(UUID id);

    Livro buscarlivroPeloId(UUID id);

    Livro criar(LivroRequestDTO livroRequestDTO);
}