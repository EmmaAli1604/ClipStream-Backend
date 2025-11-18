package com.clipstream.clipstream_api.controller;

import com.clipstream.clipstream_api.model.Genero;
import com.clipstream.clipstream_api.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/generos")
@CrossOrigin(origins = "http://localhost:5173")
public class GeneroController {

    @Autowired
    private GeneroService generoService;

    @GetMapping
    public ResponseEntity<List<Genero>> obtenerTodosLosGeneros() {
        List<Genero> generos = generoService.obtenerTodosLosGeneros();
        return ResponseEntity.ok(generos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genero> obtenerGeneroPorId(@PathVariable Long id) {
        Genero genero = generoService.obtenerGeneroPorId(id);
        if (genero != null) {
            return ResponseEntity.ok(genero);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}