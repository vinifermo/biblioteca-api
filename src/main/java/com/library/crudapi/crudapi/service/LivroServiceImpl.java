package com.library.crudapi.crudapi.service;

import com.library.crudapi.crudapi.dto.request.LivroRequestDTO;
import com.library.crudapi.crudapi.entity.Livro;
import com.library.crudapi.crudapi.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationEventPublisher;
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

    private final ApplicationEventPublisher publisher;


    public void atualizar(UUID id, LivroRequestDTO livroRequestDTO) {
        Livro livroSalvo = buscarlivroPeloId(id);
        BeanUtils.copyProperties(livroRequestDTO, livroSalvo, "id");
        livroRepository.save(livroSalvo);
    }

    public Livro buscarlivroPeloId(UUID id) {
        Livro livroSalvo = livroRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
        return livroSalvo;
    }

    public List<Livro> listar() {
        return livroRepository.findAll();
    }


    public Livro remover(UUID id) {
        livroRepository.deleteById(id);
        return null;
    }


    @Override
    public Livro criar(LivroRequestDTO livroRequestDTO) {
        Livro livro = new Livro(livroRequestDTO);
        Livro livroSalvo = livroRepository.save(livro);
        return livroSalvo;
    }
}