package com.clipstream.clipstream_api.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "resena")
public class Resena {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resenaid")
    private Long resenaId;

    @Column(name = "usuarioid", nullable = false)
    private Long usuarioId;

    @Column(name = "cortometrajeid", nullable = false)
    private Long cortometrajeId;

    @Column(name = "contenido", nullable = false, length = 500)
    private String contenido;

    @Column(name = "likes")
    private Integer likes = 0; // Cambié a Integer para coincidir con el incremento

    @CreationTimestamp
    @Column(name = "fechacreacion", updatable = false)
    private LocalDateTime fechaCreacion;

    @UpdateTimestamp
    @Column(name = "fechaactualizacion")
    private LocalDateTime fechaActualizacion;

    @Transient
    private String usuarioNombre;

    // Constructores
    public Resena() {
        this.likes = 0;
    }

    public Resena(Long usuarioId, Long cortometrajeId, String contenido) {
        this();
        this.usuarioId = usuarioId;
        this.cortometrajeId = cortometrajeId;
        this.contenido = contenido;
    }

    public Resena(Long usuarioId, Long cortometrajeId, String contenido, Integer likes) {
        this(usuarioId, cortometrajeId, contenido);
        this.likes = likes != null ? likes : 0;
    }

    // Getters y Setters
    public Long getResenaId() {
        return resenaId;
    }

    public void setResenaId(Long resenaId) {
        this.resenaId = resenaId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getCortometrajeId() {
        return cortometrajeId;
    }

    public void setCortometrajeId(Long cortometrajeId) {
        this.cortometrajeId = cortometrajeId;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes != null ? likes : 0;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    // Métodos de utilidad
    public void incrementLikes() {
        this.likes = (this.likes == null) ? 1 : this.likes + 1;
    }

    // toString para debugging
    @Override
    public String toString() {
        return "Resena{" +
                "resenaId=" + resenaId +
                ", usuarioId=" + usuarioId +
                ", cortometrajeId=" + cortometrajeId +
                ", contenido='" + contenido + '\'' +
                ", likes=" + likes +
                ", fechaCreacion=" + fechaCreacion +
                ", usuarioNombre='" + usuarioNombre + '\'' +
                '}';
    }
}