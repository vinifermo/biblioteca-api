package com.library.crudapi.crudapi.service;

import com.library.crudapi.crudapi.entity.Autor;
import com.library.crudapi.crudapi.entity.Cliente;
import com.library.crudapi.crudapi.repository.ClienteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente atualizar(Long codigo, Cliente cliente) {
        Cliente clienteSalvo = buscarClientePeloCodigo(codigo);
        BeanUtils.copyProperties(cliente, clienteSalvo, "codigo");
        Cliente save = clienteRepository.save(clienteSalvo);
        return save;

    }

    private Cliente buscarClientePeloCodigo(Long codigo) {
        Cliente clienteSalvo = clienteRepository.findById(codigo).orElse(null);
        if (clienteSalvo == null) {
            throw new EmptyResultDataAccessException(1);

        }
        return clienteSalvo;
    }


}
