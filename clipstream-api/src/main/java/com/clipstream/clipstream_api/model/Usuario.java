package com.clipstream.clipstream_api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UsuarioID")
    private Long usuarioId;

    @ManyToOne
    @JoinColumn(name = "RolID", referencedColumnName = "RolID")
    private Rol rol;

    @Column(name = "Nombre", nullable = false)
    private String nombre;

    @Column(name = "Username", nullable = false, unique = true)
    private String username;

    @Column(name = "Apellido", nullable = false)
    private String apellido;

    @Column(name = "Correo", nullable = false, unique = true)
    private String correo;

    @Column(name = "Password", nullable = false)
    private String password;

    @Column(name = "Token")
    private String token;
    
    public Usuario() {}

    public Usuario(Long usuarioId, Rol rol, String nombre, String apellido, String username, String correo, String password, String token) {
        this.usuarioId = usuarioId;
        this.rol = rol;
        this.nombre = nombre;
        this.apellido = apellido;
        this.username = username;
        this.correo = correo;
        this.password = password;
        this.token = token;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

     public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
