package com.library.crudapi.crudapi.service;

import com.library.crudapi.crudapi.dto.request.ClienteRequestDTO;
import com.library.crudapi.crudapi.entity.Cliente;

import java.util.List;
import java.util.UUID;

public interface ClienteService {

    void atualizar(UUID id, ClienteRequestDTO clienteRequestDTO);

    List<Cliente> listar();

    void remover(UUID id);

    Cliente buscarClientePeloId(UUID id);

    Cliente criar(ClienteRequestDTO clienteRequestDTO);
}