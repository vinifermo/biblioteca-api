package com.library.crudapi.crudapi.controller;

import com.library.crudapi.crudapi.entity.Autor;
import com.library.crudapi.crudapi.entity.Editora;
import com.library.crudapi.crudapi.event.RecursoCriadoEvent;
import com.library.crudapi.crudapi.repository.AutorRepository;
import com.library.crudapi.crudapi.repository.EditoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/editora")
public class EditoraController {
    @Autowired
    private EditoraRepository editoraRepository;
    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public List<Editora> listar() {
        return editoraRepository.findAll();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Editora> buscarPeloCodigo(@PathVariable Long codigo) {
        Optional<Editora> editora = editoraRepository.findById(codigo);

        return editora.isPresent() ? ResponseEntity.ok(editora.get()) : ResponseEntity.notFound().build();


    }

    @PostMapping
    public ResponseEntity<Editora> criar(@Valid @RequestBody Editora editora, HttpServletResponse response) {
        Editora editoraSalva = editoraRepository.save(editora);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, editoraSalva.getCodigo()));

        return ResponseEntity.status(HttpStatus.CREATED).body(editoraSalva);

    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long codigo) {
        editoraRepository.findById(codigo);
    }


}
