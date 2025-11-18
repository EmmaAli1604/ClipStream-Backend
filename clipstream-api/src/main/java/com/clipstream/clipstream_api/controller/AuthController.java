package com.clipstream.clipstream_api.controller;

import com.clipstream.clipstream_api.dto.LoginRequest;
import com.clipstream.clipstream_api.dto.LoginResponse;
import com.clipstream.clipstream_api.model.Usuario;
import com.clipstream.clipstream_api.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173") 
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        Optional<Usuario> usuarioOpt = authService.autenticarUsuario(
            loginRequest.getUsername(), 
            loginRequest.getPassword()
        );

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            LoginResponse response = new LoginResponse(
                usuario.getUsuarioId(),
                usuario.getUsername(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getCorreo(),
                usuario.getRol().getNombre(),
                usuario.getToken()
            );
            return ResponseEntity.ok(response);
        } else {
            LoginResponse response = new LoginResponse("Credenciales inválidas");
            return ResponseEntity.status(401).body(response);
        }
    }

    @PostMapping("/logout/{usuarioId}")
    public ResponseEntity<String> logout(@PathVariable Long usuarioId) {
        authService.cerrarSesion(usuarioId);
        return ResponseEntity.ok("Sesión cerrada exitosamente");
    }

    @GetMapping("/verificar-token/{token}")
    public ResponseEntity<LoginResponse> verificarToken(@PathVariable String token) {
        Optional<Usuario> usuarioOpt = authService.verificarToken(token);
        
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            LoginResponse response = new LoginResponse(
                usuario.getUsuarioId(),
                usuario.getUsername(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getCorreo(),
                usuario.getRol().getNombre(),
                usuario.getToken()
            );
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).build();
        }
    }
}