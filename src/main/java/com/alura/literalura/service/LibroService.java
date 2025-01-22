package com.alura.literalura.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.alura.literalura.dto.LibroApiDto;
import com.alura.literalura.dto.LibroDto;
import com.alura.literalura.dto.RespuestaApi;
import com.alura.literalura.model.Autor;
import com.alura.literalura.model.Libro;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LibroRepository;
import com.google.gson.Gson;

@Service
public class LibroService {

    private LibroRepository libroRepository;
    private AutorRepository autorRepository;

    public LibroService(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;

    }

    public Set<LibroDto> obtenerLibros() {
        return libroRepository.findAll().stream().map(LibroDto::new).collect(Collectors.toSet());
    }

    public Set<LibroDto> obtenerLibroPorNombre(String nombreLibro) throws Exception {
        RespuestaApi respuestaApi = consultaAPI(nombreLibro);
        if (respuestaApi.getCount() > 0) {
            Set<LibroDto> respuestaDB = libroRepository.findByTitleContaining(nombreLibro).stream().map(LibroDto::new).collect(Collectors.toSet());
            if (respuestaApi.getResults().stream().collect(Collectors.toSet()).equals(respuestaDB)) {
                return respuestaDB;
            } else {
                for (int i = 0; i < respuestaApi.getResults().size(); i++) {
                    LibroApiDto registro = respuestaApi.getResults().get(i);
                    if (!respuestaDB.contains(registro.toDto())) {
                        Libro nuevoLibro = registro.toEntity();
                        for (int j = 0; j < registro.getAuthors().size(); j++) {
                            Autor nuevoAutor = autorRepository.save(registro.getAuthors().get(j).toEntity());
                            nuevoLibro.getAuthors().add(nuevoAutor);
                        }
                        libroRepository.saveAndFlush(nuevoLibro);
                    }
                }
                return libroRepository.findByTitleContaining(nombreLibro).stream().map(LibroDto::new).collect(Collectors.toSet());
            }
        } else {
            return Collections.emptySet();
        }
    }

    public Set<LibroDto> obtenerLibrosPorIdioma(String idioma) {
        return libroRepository.findByLanguagesContaining(idioma.toLowerCase()).stream().map(LibroDto::new).collect(Collectors.toSet());
    }

    private RespuestaApi consultaAPI(String nombreLibro) throws Exception {
        try {
            HttpClient cliente = HttpClient.newBuilder()
                    .connectTimeout(Duration.ofSeconds(30))
                    .build();
            HttpRequest solicitud = HttpRequest.newBuilder()
                    .uri(URI.create("https://gutendex.com/books/?search=" + nombreLibro))
                    .build();
            HttpResponse<String> respuesta = cliente.send(solicitud, BodyHandlers.ofString());
            Gson gson = new Gson();
            return gson.fromJson(respuesta.body(), RespuestaApi.class);
        } catch (Exception excepcion) {
            throw new Exception("Ha ocurrido un error al tratar de obtener los datos de los libros: " + excepcion.getMessage());
        }
    }

}
