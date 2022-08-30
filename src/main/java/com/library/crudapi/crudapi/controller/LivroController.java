package com.library.crudapi.crudapi.controller;
import com.library.crudapi.crudapi.dto.request.LivroRequestDTO;
import com.library.crudapi.crudapi.entity.Livro;
import com.library.crudapi.crudapi.event.RecursoCriadoEvent;
import com.library.crudapi.crudapi.service.LivroService;
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
@RequestMapping("/livro")
public class LivroController {
    @Autowired
    ApplicationEventPublisher publisher;
    @Autowired
    private LivroService livroService;

    @GetMapping
    public List<Livro> listar() {
        return livroService.listar();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Livro> buscarPeloCodigo(@PathVariable Long codigo) {
        Optional<Livro> livro = livroService.buscarPeloCodigo(codigo);
        return livro.isPresent() ? ResponseEntity.ok(livro.get()) : ResponseEntity.notFound().build();

    }
    @PostMapping
    public ResponseEntity<Livro> criar(@Valid @RequestBody LivroRequestDTO livroRequestDTO, HttpServletResponse response) {
        Livro livroSalvo = livroService.criar(livroRequestDTO);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, livroSalvo.getCodigo()));

        return ResponseEntity.status(HttpStatus.CREATED).body(livroSalvo);

    }
    @PutMapping("/{codigo}")
    public ResponseEntity<Livro> atualizar(@PathVariable Long codigo, @Valid @RequestBody LivroRequestDTO livroRequestDTO) {
        Livro livroSalvo = livroService.atualizar(codigo, livroRequestDTO);
        return ResponseEntity.ok(livroSalvo);
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long codigo) {
        livroService.remover(codigo);
    }

}