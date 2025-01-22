package com.alura.literalura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alura.literalura.model.Autor;
import java.util.Set;


@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
    @Query("SELECT a FROM Autor a WHERE a.birth_year <= :anho AND (a.death_year > :anho OR a.death_year IS NULL)")
    public Set<Autor> findAutorVivoEnAño(@Param("anho") Integer anho);
}
