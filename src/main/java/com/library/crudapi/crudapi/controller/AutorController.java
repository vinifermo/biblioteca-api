package com.library.crudapi.crudapi.controller;


import com.library.crudapi.crudapi.entity.Autor;
import com.library.crudapi.crudapi.entity.Editora;
import com.library.crudapi.crudapi.event.RecursoCriadoEvent;
import com.library.crudapi.crudapi.repository.AutorRepository;
import com.library.crudapi.crudapi.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.awt.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/autor")
public class AutorController {

    @Autowired
    private AutorRepository autorRepository;
    @Autowired
    private ApplicationEventPublisher publisher;
    @Autowired
    private AutorService autorService;

    @GetMapping
    public List<Autor> listar() {
        return autorRepository.findAll();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Autor> buscarPeloCodigo(@PathVariable Long codigo) {
        Optional<Autor> autor = autorRepository.findById(codigo);
        return autor.isPresent() ? ResponseEntity.ok(autor.get()) : ResponseEntity.notFound().build();

    }

    @PostMapping
    public ResponseEntity<Autor> criar(@Valid @RequestBody Autor autor, HttpServletResponse response) {
        Autor autorSalvo = autorRepository.save(autor);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, autorSalvo.getCodigo()));

        return ResponseEntity.status(HttpStatus.CREATED).body(autorSalvo);

    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Autor> atualizar(@PathVariable Long codigo, @Valid @RequestBody Autor autor) {
        Autor autorSalvo = autorService.atualizar(codigo, autor);
        return ResponseEntity.ok(autorSalvo);
    }


    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long codigo) {
        autorRepository.deleteById(codigo);


    }
}
