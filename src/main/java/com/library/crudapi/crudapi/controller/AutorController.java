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

    @GetMapping("/{codigo}")
    public Optional<Autor> buscarPeloCodigo(@PathVariable Long codigo) {
        return autorService.buscarPeloCodigo(codigo);
    }

    @PostMapping
    public ResponseEntity<Autor> criar(@Valid @RequestBody AutorRequestDTO autorRequestDTO, HttpServletResponse response) {
        Autor autorSalvo = autorService.criar(autorRequestDTO);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, autorSalvo.getCodigo()));

        return ResponseEntity.status(HttpStatus.CREATED).body(autorSalvo);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Autor> atualizar(@PathVariable Long codigo, @Valid @RequestBody AutorRequestDTO autorRequestDTO) {
        Autor autorSalvo = autorService.atualizar(codigo, autorRequestDTO);
        return ResponseEntity.ok(autorSalvo);
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long codigo) {
        autorService.remover(codigo);
    }
}