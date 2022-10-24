package com.library.crudapi.crudapi.repository;

import com.library.crudapi.crudapi.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
    List<Cliente> findByClienteinfoCpfContaining(String cpf);

}