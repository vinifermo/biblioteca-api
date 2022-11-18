package com.library.crudapi.crudapi.service;


import com.library.crudapi.crudapi.dto.request.ClienteRequestDTO;
import com.library.crudapi.crudapi.entity.Cliente;
import com.library.crudapi.crudapi.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public void update(UUID id, ClienteRequestDTO clienteRequestDTO) {
        Cliente clienteSalvo = findById(id);
        BeanUtils.copyProperties(clienteRequestDTO, clienteSalvo, "id");
        clienteRepository.save(clienteSalvo);
    }

    public List<Cliente> findByClienteinfoCpfContaining(String cpf) {
        List<Cliente> clienteSalvo = clienteRepository.findByClienteinfoCpfContaining(cpf);
        return clienteSalvo;
    }

    public Cliente findById(UUID id) {
        Cliente clienteSalvo = clienteRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
        return clienteSalvo;
    }

    public void delete(@PathVariable UUID id) {
        clienteRepository.deleteById(id);
    }

    @Override
    public Cliente create(ClienteRequestDTO clienteRequestDTO) {
        Cliente cliente = new Cliente(clienteRequestDTO);
        Cliente clienteSalvo = clienteRepository.save(cliente);
        return clienteSalvo;
    }
}