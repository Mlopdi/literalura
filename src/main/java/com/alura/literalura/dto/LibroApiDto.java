package com.alura.literalura.dto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.alura.literalura.model.Libro;

public class LibroApiDto {
    private Long id;
    private String title;
    private List<String> subjects;
    private List<AutorApiDto> authors;
    private List<AutorApiDto> translators;
    private List<String> bookshelves;
    private List<String> languages;
    private Boolean copyright;
    private String media_type;
    private Map<String, String> formats;
    private Long download_count;
    
    public LibroApiDto(Long id, String title, List<String> subjects, List<AutorApiDto> authors,
            List<AutorApiDto> translators, List<String> bookshelves, List<String> languages, Boolean copyright,
            String media_type, Map<String, String> formats, Long download_count) {
        this.id = id;
        this.title = title;
        this.subjects = subjects;
        this.authors = authors;
        this.translators = translators;
        this.bookshelves = bookshelves;
        this.languages = languages;
        this.copyright = copyright;
        this.media_type = media_type;
        this.formats = formats;
        this.download_count = download_count;
    }

    public Libro toEntity() {
        return new Libro(
            title,
            String.join(",", subjects),
            String.join(", ", translators.stream().map(x -> x.getName()).collect(Collectors.toList())),
            String.join(",", bookshelves),
            String.join(",", languages),
            copyright,
            media_type,
            null,
            download_count
        );
    }

    public LibroDto toDto() {
        return new LibroDto(
            title, 
            String.join(",", subjects), 
            String.join(",", languages), 
            download_count
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    public List<AutorApiDto> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AutorApiDto> authors) {
        this.authors = authors;
    }

    public List<AutorApiDto> getTranslators() {
        return translators;
    }

    public void setTranslators(List<AutorApiDto> translators) {
        this.translators = translators;
    }

    public List<String> getBookshelves() {
        return bookshelves;
    }

    public void setBookshelves(List<String> bookshelves) {
        this.bookshelves = bookshelves;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public Boolean getCopyright() {
        return copyright;
    }

    public void setCopyright(Boolean copyright) {
        this.copyright = copyright;
    }

    public String getMedia_type() {
        return media_type;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    public Map<String, String> getFormats() {
        return formats;
    }

    public void setFormats(Map<String, String> formats) {
        this.formats = formats;
    }

    public Long getDownload_count() {
        return download_count;
    }

    public void setDownload_count(Long download_count) {
        this.download_count = download_count;
    }

    
}
