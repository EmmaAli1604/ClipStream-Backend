package com.clipstream.clipstream_api.dto;

public class RegisterResponse {
    private boolean success;
    private String mensaje;
    private Long usuarioId;
    private String username;
    private String correo;

    // Constructores
    public RegisterResponse() {}

    public RegisterResponse(boolean success, String mensaje) {
        this.success = success;
        this.mensaje = mensaje;
    }

    public RegisterResponse(boolean success, String mensaje, Long usuarioId, String username, String correo) {
        this.success = success;
        this.mensaje = mensaje;
        this.usuarioId = usuarioId;
        this.username = username;
        this.correo = correo;
    }

    // Getters y Setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}