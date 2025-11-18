package com.clipstream.clipstream_api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Rol")
public class Rol {
    @Id
    @Column(name = "RolID")
    private Integer rolId;

    @Column(name = "Nombre", nullable = false, length = 45)
    private String nombre;

    public Rol() {}

    public Rol(Integer rolId, String nombre) {
        this.rolId = rolId;
        this.nombre = nombre;
    }

    public Integer getRolId() {
        return rolId;
    }

    public void setRolId(Integer rolId) {
        this.rolId = rolId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
