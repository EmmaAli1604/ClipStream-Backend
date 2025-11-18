package com.clipstream.clipstream_api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
public class Cortometraje {
    //@Id
    //@Column(name = "CortometrajeID")
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cortometrajeId;

    @Column(name = "UsuarioID")
    private Long usuarioId;

    @Column(name = "GeneroID")
    private Long generoId;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Sinopsis")
    private String sinopsis;

    @Column(name = "Fecha")
    private LocalDate fecha;

    @Column(name = "Foto")
    private String foto;

    @Column(name = "Video")
    private String video;

    @Column(name = "Director")
    private String director;

    @Column(name = "NumVistas")
    private Integer numVistas;

    @Column(name = "Calificacion")
    private Double calificacion;

    public Cortometraje () {}

    public Cortometraje(Long cortometrajeId, Long usuarioId, Long generoId, String nombre, String sinopsis, LocalDate fecha, String foto, String video, String director, Integer numVistas, Double calificacion) {
        this.cortometrajeId = cortometrajeId;
        this.usuarioId = usuarioId;
        this.generoId = generoId;
        this.nombre = nombre;
        this.sinopsis = sinopsis;
        this.fecha = fecha;
        this.foto = foto;
        this.video = video;
        this.director = director;
        this.numVistas = numVistas;
        this.calificacion = calificacion;
    }

    public Long getCortometrajeId() {
        return cortometrajeId;
    }

    public void setCortometrajeId(Long cortometrajeId) {
        this.cortometrajeId = cortometrajeId;
    }

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
