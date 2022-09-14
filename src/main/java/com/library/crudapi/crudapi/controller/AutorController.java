package com.library.crudapi.crudapi.controller;
import com.library.crudapi.crudapi.dto.request.AutorRequestDTO;
import com.library.crudapi.crudapi.entity.Autor;
import com.library.crudapi.crudapi.event.RecursoCriadoEvent;
import com.library.crudapi.crudapi.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/autor")
public class AutorController {
    @Autowired
    private ApplicationEventPublisher publisher;
    @Autowired
    private AutorService autorService;

    @GetMapping
    public List<Autor> listar() {
        return autorService.listar();
    }

    @GetMapping("/{id}")
    public Optional<Autor> buscarPeloId(@PathVariable UUID id) {
        return autorService.buscarPeloId(id);
    }

    @PostMapping
    public ResponseEntity<Autor> criar(@Valid @RequestBody AutorRequestDTO autorRequestDTO, HttpServletResponse response) {
        Autor autorSalvo = autorService.criar(autorRequestDTO);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, autorSalvo.getId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(autorSalvo);
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