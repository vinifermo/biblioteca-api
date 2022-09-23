package com.library.crudapi.crudapi.controller;

import com.library.crudapi.crudapi.dto.request.ClienteRequestDTO;
import com.library.crudapi.crudapi.dto.response.ClienteResponseDTO;
import com.library.crudapi.crudapi.entity.Cliente;
import com.library.crudapi.crudapi.service.ClienteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public List<Cliente> listar() {
        return clienteService.listar();
    }

    @GetMapping("/{id}")
    public ClienteResponseDTO buscarClientePeloId(@PathVariable UUID id) {
        Cliente cliente = clienteService.buscarClientePeloId(id);
        ClienteResponseDTO clienteResponseDTO = new ClienteResponseDTO(cliente);

        return clienteResponseDTO;
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> criar(@Valid @RequestBody ClienteRequestDTO clienteRequestDTO) {
        Cliente clienteSalvo = clienteService.criar(clienteRequestDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(clienteSalvo.getId())
                .toUri();
        log.info("Criado novo cliente com id: {}", clienteSalvo.getId());

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable UUID id, @Valid @RequestBody ClienteRequestDTO clienteRequestDTO) {
        clienteService.atualizar(id, clienteRequestDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable UUID id) {
        clienteService.remover(id);
    }
}
