package com.library.crudapi.crudapi.controller;
import com.library.crudapi.crudapi.dto.request.ClienteRequestDTO;
import com.library.crudapi.crudapi.entity.Cliente;
import com.library.crudapi.crudapi.event.RecursoCriadoEvent;
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
    private ApplicationEventPublisher publisher;

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> listar() {
        return clienteService.listar();
    }
    @GetMapping("/{codigo}")
    public ResponseEntity<Cliente> buscarPeloCodigo(@PathVariable Long codigo) {
        Optional<Cliente> cliente = clienteService.buscarPeloCodigo(codigo);
        return cliente.isPresent() ? ResponseEntity.ok(cliente.get()) : ResponseEntity.notFound().build();
    }
    @PostMapping
    public ResponseEntity<Cliente> criar(@Valid @RequestBody ClienteRequestDTO clienteRequestDTO, HttpServletResponse response) {
        Cliente clienteSalvo = clienteService.criar(clienteRequestDTO);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, clienteSalvo.getCodigo()));

        return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);

    }
    @PutMapping("/{codigo}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long codigo, @Valid @RequestBody ClienteRequestDTO clienteRequestDTO) {
        Cliente clienteSalvo = clienteService.atualizar(codigo, clienteRequestDTO);
        return ResponseEntity.ok(clienteSalvo);
    }
    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long codigo) {
        clienteService.remover(codigo);
    }
}
