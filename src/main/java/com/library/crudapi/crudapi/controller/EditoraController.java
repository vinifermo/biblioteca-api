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

    @GetMapping("/{codigo}")
    public ResponseEntity<Editora> buscarPeloCodigo(@PathVariable Long codigo) {
        Optional<Editora> editora = editoraService.buscarPeloCodigo(codigo);
        return editora.isPresent() ? ResponseEntity.ok(editora.get()) : ResponseEntity.notFound().build();

    }
    @PostMapping
    public ResponseEntity<Editora> criar(@Valid @RequestBody EditoraRequestDTO editoraRequestDTO, HttpServletResponse response) {
        Editora editoraSalva = editoraService.criar(editoraRequestDTO);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, editoraSalva.getCodigo()));

        return ResponseEntity.status(HttpStatus.CREATED).body(editoraSalva);

    }
    @PutMapping("/{codigo}")
    public ResponseEntity<Editora> atualizar(@PathVariable Long codigo, @Valid @RequestBody EditoraRequestDTO editoraRequestDTO) {
        Editora editoraSalva = editoraService.atualizar(codigo, editoraRequestDTO);
        return ResponseEntity.ok(editoraSalva);
    }
    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long codigo) {
        editoraService.remover(codigo);
    }
}