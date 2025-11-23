package com.clipstream.clipstream_api.dto;

public class UserProfileDTO {
    private Long usuarioId;
    private String nombre;
    private String apellido;
    private String username;
    private String correo;
    
    // Constructores
    public UserProfileDTO() {}
    
    public UserProfileDTO(Long usuarioId, String nombre, String apellido, String username, String correo) {
        this.usuarioId = usuarioId;
        this.nombre = nombre;
        this.apellido = apellido;
        this.username = username;
        this.correo = correo;
    }
    
    // Getters y Setters
    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
}