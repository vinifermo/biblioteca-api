package com.library.crudapi.crudapi.controller;
import com.library.crudapi.crudapi.dto.request.EditoraRequestDTO;
import com.library.crudapi.crudapi.entity.Editora;
import com.library.crudapi.crudapi.event.RecursoCriadoEvent;
import com.library.crudapi.crudapi.service.EditoraService;
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
@RequestMapping("/editora")
public class EditoraController {
    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private EditoraService editoraService;

    @GetMapping
    public List<Editora> listar() {
        return editoraService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Editora> buscarPeloId(@PathVariable UUID id) {
        Optional<Editora> editora = editoraService.buscarPeloId(id);
        return editora.isPresent() ? ResponseEntity.ok(editora.get()) : ResponseEntity.notFound().build();

    }
    @PostMapping
    public ResponseEntity<Editora> criar(@Valid @RequestBody EditoraRequestDTO editoraRequestDTO, HttpServletResponse response) {
        Editora editoraSalva = editoraService.criar(editoraRequestDTO);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, editoraSalva.getId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(editoraSalva);

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