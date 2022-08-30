package com.library.crudapi.crudapi.service;
import com.library.crudapi.crudapi.dto.request.ClienteRequestDTO;
import com.library.crudapi.crudapi.entity.Cliente;
import java.util.List;
import java.util.Optional;
public interface ClienteService {

    Cliente atualizar(Long codigo, ClienteRequestDTO clienteRequestDTO);

    List<Cliente> listar();

    Cliente remover(Long codigo);

    Optional<Cliente> buscarPeloCodigo(Long codigo);

    Cliente criar(ClienteRequestDTO clienteRequestDTO);
}