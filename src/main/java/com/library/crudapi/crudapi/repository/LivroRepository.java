package com.library.crudapi.crudapi.repository;

import com.library.crudapi.crudapi.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {

    List<Livro> findByEditoraId(UUID id);

}