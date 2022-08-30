package com.library.crudapi.crudapi.service;
import com.library.crudapi.crudapi.dto.request.LivroRequestDTO;
import com.library.crudapi.crudapi.entity.Livro;
import java.util.List;
import java.util.Optional;

public interface LivroService {
    Livro atualizar(Long codigo, LivroRequestDTO livroRequestDTO);

    List<Livro> listar();

    Livro remover(Long codigo);

    Optional<Livro> buscarPeloCodigo(Long codigo);

    Livro criar(LivroRequestDTO livroRequestDTO);
}