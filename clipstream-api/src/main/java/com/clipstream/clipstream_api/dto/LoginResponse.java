package com.clipstream.clipstream_api.dto;

public class LoginResponse {
    private Long usuarioId;
    private String username;
    private String nombre;
    private String apellido;
    private String correo;
    private String rol;
    private String token;
    private String mensaje;

    // Constructores
    public LoginResponse() {}

    public LoginResponse(String mensaje) {
        this.mensaje = mensaje;
    }

    public LoginResponse(Long usuarioId, String username, String nombre, String apellido, String correo, String rol, String token) {
        this.usuarioId = usuarioId;
        this.username = username;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.rol = rol;
        this.token = token;
        this.mensaje = "Login exitoso";
    }

    // Getters y Setters
    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}