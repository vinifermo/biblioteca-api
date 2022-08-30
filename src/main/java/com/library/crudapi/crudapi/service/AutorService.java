package com.library.crudapi.crudapi.service;
import com.library.crudapi.crudapi.dto.request.AutorRequestDTO;
import com.library.crudapi.crudapi.entity.Autor;
import java.util.List;
import java.util.Optional;
public interface AutorService {

    Autor atualizar(Long codigo, AutorRequestDTO autorRequestDTO);

    Autor remover(Long codigo);

    List<Autor> listar();

    Optional<Autor> buscarPeloCodigo(Long codigo);

    Autor criar(AutorRequestDTO autorRequestDTO);
}