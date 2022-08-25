package com.library.crudapi.crudapi.repository;

import com.library.crudapi.crudapi.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
