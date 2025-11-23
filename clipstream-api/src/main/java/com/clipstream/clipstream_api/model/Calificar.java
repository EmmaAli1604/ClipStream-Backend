package com.clipstream.clipstream_api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Calificar")
@IdClass(CalificarId.class)
public class Calificar {
    @Id
    @Column(name = "CortometrajeID")
    private Long cortometrajeId;

    @Id
    @Column(name = "UsuarioID")
    private Long usuarioId;

    @Column(name = "Puntuacion")
    private Double puntuacion;

    public Calificar() {}

    public Calificar(Long cortometrajeId, Long usuarioId, Double puntuacion) {
        this.cortometrajeId = cortometrajeId;
        this.usuarioId = usuarioId;
        this.puntuacion = puntuacion;
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

    public Double getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Double puntuacion) {
        this.puntuacion = puntuacion;
    }
}