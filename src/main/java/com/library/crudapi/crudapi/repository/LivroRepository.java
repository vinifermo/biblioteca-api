package com.library.crudapi.crudapi.repository;
import com.library.crudapi.crudapi.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {
}