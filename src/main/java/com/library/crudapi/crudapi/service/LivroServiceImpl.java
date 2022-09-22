package com.library.crudapi.crudapi.service;

import com.library.crudapi.crudapi.dto.request.LivroRequestDTO;
import com.library.crudapi.crudapi.entity.Livro;
import com.library.crudapi.crudapi.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class LivroServiceImpl implements LivroService {

    private final LivroRepository livroRepository;

    public void atualizar(UUID id, LivroRequestDTO livroRequestDTO) {

    }

    public Livro buscarlivroPeloId(UUID id) {
        Livro livroSalvo = livroRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
        return livroSalvo;
    }

    public List<Livro> listar() {
        return livroRepository.findAll();
    }

    public void remover(UUID id) {
        livroRepository.deleteById(id);
    }

    @Override
    public Livro criar(LivroRequestDTO livroRequestDTO) {
        Livro livro = new Livro(livroRequestDTO);
        Livro livroSalvo = livroRepository.save(livro);
        return livroSalvo;
    }
}