package com.library.crudapi.crudapi.controller;

import com.library.crudapi.crudapi.dto.request.LivroRequestDTO;
import com.library.crudapi.crudapi.dto.response.LivroResponseDTO;
import com.library.crudapi.crudapi.entity.Livro;
import com.library.crudapi.crudapi.service.LivroService;
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
@RequestMapping("/livro")
public class LivroController {

    private final LivroService livroService;

    @GetMapping
    public List<Livro> findAll() {
        return livroService.findAll();
    }

    @GetMapping("/{id}")
    public LivroResponseDTO findById(@PathVariable UUID id) {
        Livro livro = livroService.findById(id);
        LivroResponseDTO livroResponseDTO = new LivroResponseDTO(livro);

        return livroResponseDTO;
    }

    @GetMapping("/editora/{id}")
    public List<Livro> findByEditoraId(@PathVariable UUID id) {
        List<Livro> livroSalvo = livroService.findByEditoraId(id);
        return livroSalvo;
    }

    @PostMapping
    public ResponseEntity<LivroResponseDTO> create(@Valid @RequestBody LivroRequestDTO livroRequestDTO) {
        Livro livroSalvo = livroService.create(livroRequestDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(livroSalvo.getId()).toUri();
        log.info("Criado nova editora com id: {}", livroSalvo.getId());

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable UUID id, @Valid @RequestBody LivroRequestDTO livroRequestDTO) {
        livroService.update(id, livroRequestDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        livroService.delete(id);
    }
}