package com.library.crudapi.crudapi.service;

import com.library.crudapi.crudapi.entity.Autor;
import com.library.crudapi.crudapi.entity.Livro;
import com.library.crudapi.crudapi.repository.AutorRepository;
import com.library.crudapi.crudapi.repository.LivroRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class LivroService {
    @Autowired
    private LivroRepository livroRepository;

    public Livro atualizar(Long codigo, Livro livro) {
        Livro livroSalvo = buscarAutorPeloCodigo(codigo);
        BeanUtils.copyProperties(livro, livroSalvo, "codigo");
        Livro save = livroRepository.save(livroSalvo);
        return save;

    }

    private Livro buscarAutorPeloCodigo(Long codigo) {
        Livro livroSalvo = livroRepository.findById(codigo).orElse(null);
        if (livroSalvo == null) {
            throw new EmptyResultDataAccessException(1);

        }
        return livroSalvo;
    }
}
