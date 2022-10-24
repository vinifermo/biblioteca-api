package com.library.crudapi.crudapi.repository;

import com.library.crudapi.crudapi.entity.Autor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface AutorRepository extends JpaRepository<Autor, UUID> {
    @Query("select autor from Autor autor")
    Page<Autor> findByPage(String filter, Pageable pageable);
}