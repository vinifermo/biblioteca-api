package com.library.crudapi.crudapi.service;

import com.library.crudapi.crudapi.dto.request.ClienteRequestDTO;
import com.library.crudapi.crudapi.entity.Cliente;

import java.util.List;
import java.util.UUID;

public interface ClienteService {

    void update(UUID id, ClienteRequestDTO clienteRequestDTO);

    List<Cliente> findAll();
    List<Cliente> findByClienteinfoCpfContaining(String cpf);

    void delete(UUID id);

    Cliente findById(UUID id);

    Cliente create(ClienteRequestDTO clienteRequestDTO);
}