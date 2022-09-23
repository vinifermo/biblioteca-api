package com.library.crudapi.crudapi.service;

import com.library.crudapi.crudapi.dto.request.EditoraRequestDTO;
import com.library.crudapi.crudapi.entity.Editora;
import com.library.crudapi.crudapi.repository.EditoraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EditoraServiceImpl implements EditoraService {

    private final EditoraRepository editoraRepository;

    public void atualizar(UUID id, EditoraRequestDTO editoraRequestDTO) {
        Editora editoraSalva = buscarEditoraPeloId(id);
        BeanUtils.copyProperties(editoraRequestDTO, editoraSalva, "id");
        editoraRepository.save(editoraSalva);
    }

    public Editora buscarEditoraPeloId(UUID id) {
        Editora editoraSalva = editoraRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
        return editoraSalva;
    }

    public List<Editora> listar() {
        return editoraRepository.findAll();
    }

    public void remover(@PathVariable UUID id) {
        editoraRepository.deleteById(id);
    }

    public Editora criar(EditoraRequestDTO editoraRequestDTO) {
        Editora editora = new Editora(editoraRequestDTO);
        Editora editoraSalva = editoraRepository.save(editora);
        return editoraSalva;
    }
}