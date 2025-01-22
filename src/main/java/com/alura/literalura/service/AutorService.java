package com.alura.literalura.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.alura.literalura.dto.AutorDto;
import com.alura.literalura.repository.AutorRepository;

@Service
public class AutorService {

    private AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public Set<AutorDto> obtenerAutores() {
        return autorRepository.findAll().stream().map(AutorDto::new).collect(Collectors.toSet());
    }

    public Set<AutorDto> obtenerSiEstabaVivo(Integer anho) {
        return autorRepository.findAutorVivoEnAño(anho)
            .stream().map(AutorDto::new).collect(Collectors.toSet());
    }

}
