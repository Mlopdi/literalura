package com.alura.literalura.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
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
@Table(name = "LIBRO")
public class Libro implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLibro;
    @Column(length = 500)
    private String title;
    private String subjects;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "LIBRO_AUTOR_INTERMEDIA",
        joinColumns = @JoinColumn(name = "idLibro"),
        inverseJoinColumns = @JoinColumn(name = "idAutor")
    )
    private Set<Autor> authors = new HashSet<>();
    private String translators;
    private String bookshelves;
    private String languages;
    private Boolean copyright;
    private String media_type;
    private String formats;
    private Long download_count;

    public Libro() {}

    public Libro(String title, String subjects, String translators, String bookshelves,
            String languages, Boolean copyright, String media_type, String formats, Long download_count) {
        this.title = title;
        this.subjects = subjects;
        this.translators = translators;
        this.bookshelves = bookshelves;
        this.languages = languages;
        this.copyright = copyright;
        this.media_type = media_type;
        this.formats = formats;
        this.download_count = download_count;
    }

    public Long getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(Long idLibro) {
        this.idLibro = idLibro;
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

    public Set<Autor> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Autor> authors) {
        this.authors = authors;
    }

    public String getTranslators() {
        return translators;
    }

    public void setTranslators(String translators) {
        this.translators = translators;
    }

    public String getBookshelves() {
        return bookshelves;
    }

    public void setBookshelves(String bookshelves) {
        this.bookshelves = bookshelves;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
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

    public String getFormats() {
        return formats;
    }

    public void setFormats(String formats) {
        this.formats = formats;
    }

    public Long getDownload_count() {
        return download_count;
    }

    public void setDownload_count(Long download_count) {
        this.download_count = download_count;
    }

    
}
