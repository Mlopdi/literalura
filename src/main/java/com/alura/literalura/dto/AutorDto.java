package com.alura.literalura.dto;

import java.util.Set;
import java.util.stream.Collectors;

import com.alura.literalura.model.Autor;

public class AutorDto {
    private String name;
    private Integer birth_year;
    private Integer death_year;
	private Set<LibroDto> libros;

    public AutorDto() {}

    public AutorDto(Autor autor) {
        this.name = autor.getName();
        this.birth_year = autor.getBirth_year();
        this.death_year = autor.getDeath_year();
        this.libros = autor.getLibros().stream().map(LibroDto::getParsed).collect(Collectors.toSet());
    }

    public AutorDto(String name, Integer birth_year, Integer death_year) {
        this.name = name;
        this.birth_year = birth_year;
        this.death_year = death_year;
    }

    public static AutorDto getParsed(Autor autor) {
        return new AutorDto(autor.getName(), autor.getBirth_year(), autor.getDeath_year());
    }

    @Override
    public String toString() {
        return String.format("""
                ------------- AUTOR -------------
                Autor: %s
                Fecha de nacimiento: %d
                Fecha de fallecimiento: %d
                Libros: [%s]
                ---------------------------------

                """, name, birth_year, death_year, String.join(", ", libros.stream().map(x -> x.getTitle()).collect(Collectors.toList())));
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

    public Set<LibroDto> getLibros() {
        return libros;
    }

    public void setLibros(Set<LibroDto> libros) {
        this.libros = libros;
    }

}
