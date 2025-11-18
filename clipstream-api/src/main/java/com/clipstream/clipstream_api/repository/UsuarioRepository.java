package com.clipstream.clipstream_api.repository;

import com.clipstream.clipstream_api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    // Buscar por username
    Optional<Usuario> findByUsername(String username);
    
    // Buscar por correo
    Optional<Usuario> findByCorreo(String correo);
    
    // Buscar por username o correo
    @Query("SELECT u FROM Usuario u WHERE u.username = :usernameOrEmail OR u.correo = :usernameOrEmail")
    Optional<Usuario> findByUsernameOrCorreo(@Param("usernameOrEmail") String usernameOrEmail);
}