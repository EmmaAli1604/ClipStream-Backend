package com.clipstream.clipstream_api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Genero")
public class Genero {
    @Id
    @Column(name = "GeneroID")
    private Long generoId;

    @Column(name = "Nombregenero", nullable = false)
    private String nombreGenero;

    public Genero() {}

    public Long getGeneroId() {
        return generoId;
    }

    public void setGeneroId(Long generoId) {
        this.generoId = generoId;
    }

    public String getNombreGenero() {
        return nombreGenero;
    }

    public void setNombreGenero(String nombreGenero) {
        this.nombreGenero = nombreGenero;
    }
}