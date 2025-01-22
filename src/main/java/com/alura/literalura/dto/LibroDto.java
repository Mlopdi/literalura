package com.alura.literalura.dto;

import java.util.Set;
import java.util.stream.Collectors;

import com.alura.literalura.model.Libro;

public class LibroDto {
    private String title;
    private String subjects;
    private Set<AutorDto> authors;
    private String languages;
    private Long download_count;

    public LibroDto() {}

    public LibroDto(Libro libro) {
        this.title = libro.getTitle();
        this.subjects = libro.getSubjects();
        this.authors = libro.getAuthors().stream().map(AutorDto::getParsed).collect(Collectors.toSet());
        this.languages = libro.getLanguages();
        this.download_count = libro.getDownload_count();
    }

    public LibroDto(String title, String subjects, String languages, Long download_count) {
        this.title = title;
        this.subjects = subjects;
        this.languages = languages;
        this.download_count = download_count;
    }

    public static LibroDto getParsed(Libro libro) {
        return new LibroDto(libro.getTitle(), libro.getSubjects(), libro.getLanguages(), libro.getDownload_count());
    }

    public static LibroDto getParsedFromApi(LibroApiDto libro) {
        return new LibroDto(libro.getTitle(), String.join(", ", libro.getSubjects()), String.join(", ", libro.getLanguages()), libro.getDownload_count());
    }

    @Override
    public String toString() {
        return String.format("""
                ------------- LIBRO -------------
                Título: %s
                Autor: %s
                Idioma: %s
                Número de descargas: %d
                ---------------------------------

                """, title, String.join(", ", authors.stream().map(x -> x.getName()).collect(Collectors.toList())), languages, download_count);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }

    public Set<AutorDto> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<AutorDto> authors) {
        this.authors = authors;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public Long getDownload_count() {
        return download_count;
    }

    public void setDownload_count(Long download_count) {
        this.download_count = download_count;
    }
    
}
