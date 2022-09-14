package com.library.crudapi.crudapi.service;
import com.library.crudapi.crudapi.dto.request.ClienteRequestDTO;
import com.library.crudapi.crudapi.entity.Cliente;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClienteService {

    Cliente atualizar(UUID id, ClienteRequestDTO clienteRequestDTO);

    List<Cliente> listar();

    Cliente remover(UUID id);

    Optional<Cliente> buscarPeloId(UUID id);

    Cliente criar(ClienteRequestDTO clienteRequestDTO);
}