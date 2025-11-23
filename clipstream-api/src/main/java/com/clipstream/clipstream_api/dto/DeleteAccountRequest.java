package com.clipstream.clipstream_api.dto;

public class DeleteAccountRequest {
    private String password;

    // Constructores
    public DeleteAccountRequest() {}

    // Getters y Setters
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}