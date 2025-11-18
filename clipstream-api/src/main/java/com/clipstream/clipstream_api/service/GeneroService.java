package com.clipstream.clipstream_api.service;

import com.clipstream.clipstream_api.model.Genero;
import com.clipstream.clipstream_api.repository.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneroService {

    @Autowired
    private GeneroRepository generoRepository;

    public List<Genero> obtenerTodosLosGeneros() {
        return generoRepository.findAllByOrderByNombreGeneroAsc();
    }

    public Genero obtenerGeneroPorId(Long id) {
        return generoRepository.findById(id).orElse(null);
    }

    public boolean existeGenero(Long id) {
        return generoRepository.existsById(id);
    }
}