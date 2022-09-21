package com.library.crudapi.crudapi.controller;

import com.library.crudapi.crudapi.dto.request.LivroRequestDTO;
import com.library.crudapi.crudapi.dto.response.LivroResponseDTO;
import com.library.crudapi.crudapi.entity.Livro;
import com.library.crudapi.crudapi.service.LivroService;
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
@RequestMapping("/livro")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @GetMapping
    public List<Livro> listar() {
        return livroService.listar();
    }

    @GetMapping("/{id}")
    public LivroResponseDTO buscarlivroPeloId(@PathVariable UUID id) {
        Livro livro = livroService.buscarlivroPeloId(id);
        LivroResponseDTO livroResponseDTO = new LivroResponseDTO(livro);
        return livroResponseDTO;

    }

    @PostMapping
    public ResponseEntity<Livro> criar(@Valid @RequestBody LivroRequestDTO livroRequestDTO, HttpServletResponse response) {
        Livro livroSalvo = livroService.criar(livroRequestDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(livroSalvo.getId())
                .toUri();
        log.info("Criado nova editora com id: {}", livroSalvo.getId());
        return ResponseEntity.created(location).build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizar(@PathVariable UUID id, @Valid @RequestBody LivroRequestDTO livroRequestDTO) {
        livroService.atualizar(id, livroRequestDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable UUID id) {
        livroService.remover(id);
    }

}