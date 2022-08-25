package com.library.crudapi.crudapi.repository;

import com.library.crudapi.crudapi.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro,Long> {
}
