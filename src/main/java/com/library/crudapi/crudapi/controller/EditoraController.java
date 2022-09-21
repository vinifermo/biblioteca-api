package com.library.crudapi.crudapi.controller;

import com.library.crudapi.crudapi.dto.request.EditoraRequestDTO;
import com.library.crudapi.crudapi.dto.response.EditoraResponseDTO;
import com.library.crudapi.crudapi.entity.Editora;
import com.library.crudapi.crudapi.service.EditoraService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/editora")
public class EditoraController {


    @Autowired
    private EditoraService editoraService;

    @GetMapping
    public List<Editora> listar() {
        return editoraService.listar();
    }

    @GetMapping("/{id}")
    public EditoraResponseDTO buscarlivroPeloId(@PathVariable UUID id) {
        Editora editora = editoraService.buscarEditoraPeloId(id);
        EditoraResponseDTO editoraResponseDTO = new EditoraResponseDTO(editora);
        return editoraResponseDTO;
    }

    @PostMapping
    public ResponseEntity<Editora> criar(@Valid @RequestBody EditoraRequestDTO editoraRequestDTO, HttpServletResponse response) {
        Editora editoraSalva = editoraService.criar(editoraRequestDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(editoraSalva.getId())
                .toUri();
        log.info("Criado nova editora com id: {}", editoraSalva.getId());
        return ResponseEntity.created(location).build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Editora> atualizar(@PathVariable UUID id, @Valid @RequestBody EditoraRequestDTO editoraRequestDTO) {
        Editora editoraSalva = editoraService.atualizar(id, editoraRequestDTO);
        return ResponseEntity.ok(editoraSalva);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable UUID id) {
        editoraService.remover(id);
    }
}