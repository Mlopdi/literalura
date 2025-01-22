package com.alura.literalura.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "AUTOR")
public class Autor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAutor;
    private String name;
    private Integer birth_year;
    private Integer death_year;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "LIBRO_AUTOR_INTERMEDIA",
        joinColumns = @JoinColumn(name = "idAutor"),
        inverseJoinColumns = @JoinColumn(name = "idLibro")
    )
	private Set<Libro> libros = new HashSet<>();;

    public Autor() {}

    public Autor(String name, Integer birth_year, Integer death_year, Set<Libro> libros) {
        this.name = name;
        this.birth_year = birth_year;
        this.death_year = death_year;
        this.libros = libros;
    }

    public Autor(String name, Integer birth_year, Integer death_year) {
        this.name = name;
        this.birth_year = birth_year;
        this.death_year = death_year;
    }

    public Long getIdAutor() {
        return idAutor;
    }
    public void setIdAutor(Long idAutor) {
        this.idAutor = idAutor;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getBirth_year() {
        return birth_year;
    }
    public void setBirth_year(Integer birth_year) {
        this.birth_year = birth_year;
    }
    public Integer getDeath_year() {
        return death_year;
    }
    public void setDeath_year(Integer death_year) {
        this.death_year = death_year;
    }
    public Set<Libro> getLibros() {
        return libros;
    }
    public void setLibros(Set<Libro> libros) {
        this.libros = libros;
    }

    
}
