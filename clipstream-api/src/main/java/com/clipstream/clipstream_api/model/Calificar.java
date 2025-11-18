package com.clipstream.clipstream_api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@IdClass(CalificarId.class)
@Table(name = "Calificar")
public class Calificar {
    @Id
    @Column(name = "Cortometrajeid")
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
