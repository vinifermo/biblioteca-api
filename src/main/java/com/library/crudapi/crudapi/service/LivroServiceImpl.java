package com.library.crudapi.crudapi.service;

import com.library.crudapi.crudapi.dto.request.LivroRequestDTO;
import com.library.crudapi.crudapi.entity.Livro;
import com.library.crudapi.crudapi.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LivroServiceImpl implements LivroService {

    private final LivroRepository livroRepository;

    public void update(UUID id, LivroRequestDTO livroRequestDTO) {
        Livro livroSalvo = findById(id);
        BeanUtils.copyProperties(livroRequestDTO, livroSalvo, "id");
        livroRepository.save(livroSalvo);
    }

    public Livro findById(UUID id) {
        Livro livroSalvo = livroRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
        return livroSalvo;
    }

    public List<Livro> findAll() {
        return livroRepository.findAll();
    }

    public List<Livro> findByEditoraId(UUID id) {
        List<Livro> livroSalvo = livroRepository.findByEditoraId(id);
        return livroSalvo;
    }

    public void delete(UUID id) {
        livroRepository.deleteById(id);
    }

    @Override
    public Livro create(LivroRequestDTO livroRequestDTO) {
        Livro livro = new Livro(livroRequestDTO);
        Livro livroSalvo = livroRepository.save(livro);
        return livroSalvo;
    }
}