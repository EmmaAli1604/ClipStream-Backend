package com.clipstream.clipstream_api.model;

import java.io.Serializable;

public class CalificarId implements Serializable {

    private Long cortometrajeId;
    private Long usuarioId;

    public CalificarId() {}

    public CalificarId(Long cortometrajeId, Long usuarioId) {
        this.cortometrajeId = cortometrajeId;
        this.usuarioId = usuarioId;
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
}

