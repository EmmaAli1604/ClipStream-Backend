package com.clipstream.clipstream_api.service;

import com.clipstream.clipstream_api.dto.RegisterResponse;
import com.clipstream.clipstream_api.dto.UserProfileDTO;
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

    public UserProfileDTO getUserProfile(Long id) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
        
        if (usuarioOpt.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado con ID: " + id);
        }
        
        Usuario usuario = usuarioOpt.get();
        
        return new UserProfileDTO(
            usuario.getUsuarioId(),
            usuario.getNombre(),
            usuario.getApellido(),
            usuario.getUsername(),
            usuario.getCorreo()
        );
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

    public RegisterResponse actualizarPerfil(Long id, String nombre, String apellido, String username, String correo) {
        try {
            Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
            if (usuarioOpt.isEmpty()) {
                return new RegisterResponse(false, "Usuario no encontrado");
            }

            Usuario usuario = usuarioOpt.get();

            // Verificar si el username ya existe (excluyendo el usuario actual)
            Optional<Usuario> usuarioConUsername = usuarioRepository.findByUsername(username);
            if (usuarioConUsername.isPresent() && !usuarioConUsername.get().getUsuarioId().equals(id)) {
                return new RegisterResponse(false, "El nombre de usuario ya está en uso");
            }

            // Verificar si el correo ya existe (excluyendo el usuario actual)
            Optional<Usuario> usuarioConCorreo = usuarioRepository.findByCorreo(correo);
            if (usuarioConCorreo.isPresent() && !usuarioConCorreo.get().getUsuarioId().equals(id)) {
                return new RegisterResponse(false, "El correo electrónico ya está registrado");
            }

            // Actualizar campos
            usuario.setNombre(nombre);
            usuario.setApellido(apellido);
            usuario.setUsername(username);
            usuario.setCorreo(correo);

            usuarioRepository.save(usuario);

            return new RegisterResponse(true, "Perfil actualizado exitosamente", 
                usuario.getUsuarioId(), usuario.getUsername(), usuario.getCorreo());

        } catch (Exception e) {
            return new RegisterResponse(false, "Error al actualizar el perfil: " + e.getMessage());
        }
    }

    // Método para cambiar contraseña
    public RegisterResponse cambiarPassword(Long id, String currentPassword, String newPassword) {
        try {
            Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
            if (usuarioOpt.isEmpty()) {
                return new RegisterResponse(false, "Usuario no encontrado");
            }

            Usuario usuario = usuarioOpt.get();

            // Verificar contraseña actual
            if (!usuario.getPassword().equals(currentPassword)) {
                return new RegisterResponse(false, "La contraseña actual es incorrecta");
            }

            // Verificar que la nueva contraseña no sea igual a la actual
            if (currentPassword.equals(newPassword)) {
                return new RegisterResponse(false, "La nueva contraseña debe ser diferente a la actual");
            }

            // Actualizar contraseña
            usuario.setPassword(newPassword);
            usuarioRepository.save(usuario);

            return new RegisterResponse(true, "Contraseña cambiada exitosamente");

        } catch (Exception e) {
            return new RegisterResponse(false, "Error al cambiar la contraseña: " + e.getMessage());
        }
    }

    // Método para eliminar cuenta
    public RegisterResponse eliminarCuenta(Long id, String password) {
        try {
            Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
            if (usuarioOpt.isEmpty()) {
                return new RegisterResponse(false, "Usuario no encontrado");
            }

            Usuario usuario = usuarioOpt.get();

            // Verificar contraseña
            if (!usuario.getPassword().equals(password)) {
                return new RegisterResponse(false, "Contraseña incorrecta");
            }

            // Eliminar usuario
            usuarioRepository.delete(usuario);

            return new RegisterResponse(true, "Cuenta eliminada exitosamente");

        } catch (Exception e) {
            return new RegisterResponse(false, "Error al eliminar la cuenta: " + e.getMessage());
        }
    }
}