package com.library.crudapi.crudapi.controller;

import com.library.crudapi.crudapi.dto.request.EditoraRequestDTO;
import com.library.crudapi.crudapi.dto.response.EditoraResponseDTO;
import com.library.crudapi.crudapi.entity.Editora;
import com.library.crudapi.crudapi.service.EditoraService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/editora")
public class EditoraController {

    private final EditoraService editoraService;

    @GetMapping
    public List<Editora> findAll() {
        return editoraService.findAll();
    }

    @GetMapping("/{id}")
    public EditoraResponseDTO findById(@PathVariable UUID id) {
        Editora editora = editoraService.findById(id);
        EditoraResponseDTO editoraResponseDTO = new EditoraResponseDTO(editora);

        return editoraResponseDTO;
    }

    @PostMapping
    public ResponseEntity<EditoraResponseDTO> create(@Valid @RequestBody EditoraRequestDTO editoraRequestDTO) {
        Editora editoraSalva = editoraService.create(editoraRequestDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(editoraSalva.getId())
                .toUri();
        log.info("Criado nova editora com id: {}", editoraSalva.getId());

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable UUID id, @Valid @RequestBody EditoraRequestDTO editoraRequestDTO) {
        editoraService.update(id, editoraRequestDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        editoraService.delete(id);
    }
}