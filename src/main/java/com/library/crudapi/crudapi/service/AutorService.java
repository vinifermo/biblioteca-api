package com.library.crudapi.crudapi.service;

import com.library.crudapi.crudapi.entity.Autor;
import com.library.crudapi.crudapi.repository.AutorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class AutorService {
    @Autowired
    private AutorRepository autorRepository;

    public Autor atualizar(Long codigo, Autor autor) {
        Autor autorSalvo = buscarAutorPeloCodigo(codigo);
        BeanUtils.copyProperties(autor, autorSalvo, "codigo");
        Autor save = autorRepository.save(autorSalvo);
        return save;

    }

    private Autor buscarAutorPeloCodigo(Long codigo) {
        Autor autorSalvo = autorRepository.findById(codigo).orElse(null);
        if (autorSalvo == null) {
            throw new EmptyResultDataAccessException(1);

        }
        return autorSalvo;
    }

}
