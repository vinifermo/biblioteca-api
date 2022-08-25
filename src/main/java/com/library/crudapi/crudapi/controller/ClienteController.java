package com.library.crudapi.crudapi.controller;

import com.library.crudapi.crudapi.entity.Autor;
import com.library.crudapi.crudapi.entity.Cliente;
import com.library.crudapi.crudapi.event.RecursoCriadoEvent;
import com.library.crudapi.crudapi.repository.AutorRepository;
import com.library.crudapi.crudapi.repository.ClienteRepository;
import com.library.crudapi.crudapi.service.AutorService;
import com.library.crudapi.crudapi.service.ClienteService;
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
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Cliente> buscarPeloCodigo(@PathVariable Long codigo) {
        Optional<Cliente> cliente = clienteRepository.findById(codigo);
        return cliente.isPresent() ? ResponseEntity.ok(cliente.get()) : ResponseEntity.notFound().build();

    }

    @PostMapping
    public ResponseEntity<Cliente> criar(@Valid @RequestBody Cliente cliente, HttpServletResponse response) {
        Cliente clienteSalvo = clienteRepository.save(cliente);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, clienteSalvo.getCodigo()));

        return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);

    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long codigo, @Valid @RequestBody Cliente cliente) {
        Cliente clienteSalvo = clienteService.atualizar(codigo, cliente);
        return ResponseEntity.ok(clienteSalvo);
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long codigo) {
        clienteRepository.deleteById(codigo);


    }


}


