package com.clipstream.clipstream_api.dto;

import java.time.LocalDate;

public class CortometrajeRequest {
    private Long usuarioId;
    private Long generoId;
    private String nombre;
    private String sinopsis;
    private LocalDate fecha;
    private String foto;
    private String video;
    private String director;
    private Integer numVistas;
    private Double calificacion;

    // Constructores
    public CortometrajeRequest() {}

    public CortometrajeRequest(Long usuarioId, Long generoId, String nombre, String sinopsis, LocalDate fecha, String foto, String video, String director) {
        this.usuarioId = usuarioId;
        this.generoId = generoId;
        this.nombre = nombre;
        this.sinopsis = sinopsis;
        this.fecha = fecha;
        this.foto = foto;
        this.video = video;
        this.director = director;
        this.numVistas = 0;
        this.calificacion = 0.0;
    }

    // Getters y Setters
    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getGeneroId() {
        return generoId;
    }

    public void setGeneroId(Long generoId) {
        this.generoId = generoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Integer getNumVistas() {
        return numVistas;
    }

    public void setNumVistas(Integer numVistas) {
        this.numVistas = numVistas;
    }

    public Double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }
}