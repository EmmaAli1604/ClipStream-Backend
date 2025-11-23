package com.clipstream.clipstream_api.model;

import java.io.Serializable;
import java.util.Objects;

public class CalificarId implements Serializable {
    private Long cortometrajeId;
    private Long usuarioId;

    // Constructores
    public CalificarId() {}

    public CalificarId(Long cortometrajeId, Long usuarioId) {
        this.cortometrajeId = cortometrajeId;
        this.usuarioId = usuarioId;
    }

    // Getters y Setters
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CalificarId that = (CalificarId) o;
        return Objects.equals(cortometrajeId, that.cortometrajeId) &&
               Objects.equals(usuarioId, that.usuarioId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cortometrajeId, usuarioId);
    }
}