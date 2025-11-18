package com.clipstream.clipstream_api.service;

import com.clipstream.clipstream_api.dto.RegisterResponse;
import com.clipstream.clipstream_api.model.Rol;
import com.clipstream.clipstream_api.model.Usuario;
import com.clipstream.clipstream_api.repository.RolRepository;
import com.clipstream.clipstream_api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    public RegisterResponse registrarUsuario(String nombre, String apellido, String username, String correo, String password) {
        // Verificar si el username ya existe
        if (usuarioRepository.findByUsername(username).isPresent()) {
            return new RegisterResponse(false, "El nombre de usuario ya está en uso");
        }

        // Verificar si el correo ya existe
        if (usuarioRepository.findByCorreo(correo).isPresent()) {
            return new RegisterResponse(false, "El correo electrónico ya está registrado");
        }

        try {
            // Obtener el rol por defecto (asumiendo que el rol 1 es "Usuario normal")
            Optional<Rol> rolOpt = rolRepository.findById(1);
            if (rolOpt.isEmpty()) {
                return new RegisterResponse(false, "Error en la configuración del sistema");
            }

            // Crear nuevo usuario
            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setRol(rolOpt.get());
            nuevoUsuario.setNombre(nombre);
            nuevoUsuario.setApellido(apellido);
            nuevoUsuario.setUsername(username);
            nuevoUsuario.setCorreo(correo);
            nuevoUsuario.setPassword(password);
            nuevoUsuario.setToken(null);

            // Guardar usuario (el ID se generará automáticamente si usas @GeneratedValue)
            Usuario usuarioGuardado = usuarioRepository.save(nuevoUsuario);

            return new RegisterResponse(
                true, 
                "Usuario registrado exitosamente", 
                usuarioGuardado.getUsuarioId(), 
                usuarioGuardado.getUsername(), 
                usuarioGuardado.getCorreo()
            );

        } catch (Exception e) {
            return new RegisterResponse(false, "Error al registrar el usuario: " + e.getMessage());
        }
    }

    // Otros métodos relacionados con usuarios
    public Optional<Usuario> obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public boolean existeUsuarioPorUsername(String username) {
        return usuarioRepository.findByUsername(username).isPresent();
    }

    public boolean existeUsuarioPorCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo).isPresent();
    }
}