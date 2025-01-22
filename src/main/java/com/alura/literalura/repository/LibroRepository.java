package com.alura.literalura.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alura.literalura.model.Libro;



@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {
    public Set<Libro> findByTitleContaining(String title);
    public Set<Libro> findByLanguagesContaining(String language);
}
