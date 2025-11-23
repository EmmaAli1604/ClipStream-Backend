package com.clipstream.clipstream_api.dto;

public class CalificacionRequest {
    private Long usuarioId;
    private Long cortometrajeId;
    private Double puntuacion;

    public CalificacionRequest() {}

    public CalificacionRequest(Long usuarioId, Long cortometrajeId, Double puntuacion) {
        this.usuarioId = usuarioId;
        this.cortometrajeId = cortometrajeId;
        this.puntuacion = puntuacion;
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

    public Double getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Double puntuacion) {
        this.puntuacion = puntuacion;
    }
}