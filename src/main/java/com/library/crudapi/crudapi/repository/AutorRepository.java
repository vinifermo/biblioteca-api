package com.library.crudapi.crudapi.repository;

import com.library.crudapi.crudapi.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

    public interface AutorRepository extends JpaRepository<Autor, Long> {
}
