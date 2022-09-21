package com.library.crudapi.crudapi.controller;

import com.library.crudapi.crudapi.dto.request.AutorRequestDTO;
import com.library.crudapi.crudapi.dto.response.AutorResponseDTO;
import com.library.crudapi.crudapi.entity.Autor;
import com.library.crudapi.crudapi.service.AutorService;
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
@RequestMapping("/autor")
public class AutorController {
    @Autowired
    private AutorService autorService;

    @GetMapping
    public List<Autor> listar() {
        return autorService.listar();
    }

    @GetMapping("/{id}")
    public AutorResponseDTO buscarAutorPeloId(@PathVariable UUID id) {
        Autor autor = autorService.buscarAutorPeloId(id);
        AutorResponseDTO autorResponseDTO = new AutorResponseDTO(autor);
        return autorResponseDTO;

    }

    @PostMapping
    public ResponseEntity<Autor> criar(@Valid @RequestBody AutorRequestDTO autorRequestDTO, HttpServletResponse response) {
        Autor autorSalvo = autorService.criar(autorRequestDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(autorSalvo.getId())
                .toUri();
        log.info("Criado novo autor com id: {}", autorSalvo.getId());
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Autor> atualizar(@PathVariable UUID id, @Valid @RequestBody AutorRequestDTO autorRequestDTO) {
        Autor autorSalvo = autorService.atualizar(id, autorRequestDTO);
        return ResponseEntity.ok(autorSalvo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable UUID id) {
        autorService.remover(id);
    }
}