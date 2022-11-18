package com.library.crudapi.crudapi.service;

import com.library.crudapi.crudapi.dto.request.AutorRequestDTO;
import com.library.crudapi.crudapi.entity.Autor;
import com.library.crudapi.crudapi.repository.AutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AutorServiceImpl implements AutorService {

    private final AutorRepository autorRepository;

    public void update(UUID id, AutorRequestDTO autorRequestDTO) {
        Autor autorSalvo = findById(id);
        BeanUtils.copyProperties(autorRequestDTO, autorSalvo, "id");
        autorRepository.save(autorSalvo);
    }

    public Page<Autor> findByPage(String filter, Pageable pageable) {
        return autorRepository.findByPage(filter, pageable);
    }

    public Autor findById(UUID id) {
        Autor autorSalvo = autorRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
        return autorSalvo;
    }

    @Override
    public Autor create(AutorRequestDTO autorRequestDTO) {
        Autor autor = new Autor(autorRequestDTO);
        Autor autorSalvo = autorRepository.save(autor);
        return autorSalvo;
    }


    public void delete(UUID id) {
        autorRepository.deleteById(id);
    }
}