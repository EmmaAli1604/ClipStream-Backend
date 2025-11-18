package com.clipstream.clipstream_api.service;

import com.clipstream.clipstream_api.model.Usuario;
import com.clipstream.clipstream_api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Optional<Usuario> autenticarUsuario(String usernameOrEmail, String password) {
        // Buscar usuario por username o email
        Optional<Usuario> usuarioOpt = usuarioRepository.findByUsernameOrCorreo(usernameOrEmail);
        
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            // Verificar contraseña (en producción deberías usar BCrypt)
            if (usuario.getPassword().equals(password)) {
                // Generar token de sesión
                String token = generarToken();
                usuario.setToken(token);
                usuarioRepository.save(usuario);
                return Optional.of(usuario);
            }
        }
        return Optional.empty();
    }

    public Optional<Usuario> verificarToken(String token) {
        return usuarioRepository.findAll().stream()
                .filter(usuario -> token.equals(usuario.getToken()))
                .findFirst();
    }

    public void cerrarSesion(Long usuarioId) {
        usuarioRepository.findById(usuarioId).ifPresent(usuario -> {
            usuario.setToken(null);
            usuarioRepository.save(usuario);
        });
    }

    private String generarToken() {
        return UUID.randomUUID().toString();
    }
}