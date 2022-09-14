package com.library.crudapi.crudapi.repository;
import com.library.crudapi.crudapi.entity.Editora;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EditoraRepository extends JpaRepository<Editora, UUID> {
}