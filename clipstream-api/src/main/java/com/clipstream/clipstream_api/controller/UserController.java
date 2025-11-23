package com.clipstream.clipstream_api.controller;

import com.clipstream.clipstream_api.dto.ChangePasswordRequest;
import com.clipstream.clipstream_api.dto.DeleteAccountRequest;
import com.clipstream.clipstream_api.dto.RegisterRequest;
import com.clipstream.clipstream_api.dto.RegisterResponse;
import com.clipstream.clipstream_api.dto.UpdateProfileRequest;
import com.clipstream.clipstream_api.dto.UserProfileDTO;
import com.clipstream.clipstream_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest registerRequest) {
        RegisterResponse response = userService.registrarUsuario(
            registerRequest.getNombre(),
            registerRequest.getApellido(),
            registerRequest.getUsername(),
            registerRequest.getCorreo(),
            registerRequest.getPassword()
        );

        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }

        @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        try {
            UserProfileDTO userProfile = userService.getUserProfile(id);
            return ResponseEntity.ok(userProfile);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody RegisterRequest updateRequest) {
        // Lógica para actualizar usuario
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/profile")
    public ResponseEntity<?> updateUserProfile(@PathVariable Long id, @RequestBody UpdateProfileRequest updateRequest) {
        try {
            RegisterResponse response = userService.actualizarPerfil(
                id,
                updateRequest.getNombre(),
                updateRequest.getApellido(),
                updateRequest.getUsername(),
                updateRequest.getCorreo()
            );

            if (response.isSuccess()) {
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new RegisterResponse(false, "Error al actualizar el perfil: " + e.getMessage()));
        }
    }

    // PUT - Cambiar contraseña
    @PutMapping("/{id}/password")
    public ResponseEntity<?> changePassword(@PathVariable Long id, @RequestBody ChangePasswordRequest changeRequest) {
        try {
            RegisterResponse response = userService.cambiarPassword(
                id,
                changeRequest.getCurrentPassword(),
                changeRequest.getNewPassword()
            );

            if (response.isSuccess()) {
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new RegisterResponse(false, "Error al cambiar la contraseña: " + e.getMessage()));
        }
    }

    // DELETE - Eliminar cuenta
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long id, @RequestBody DeleteAccountRequest deleteRequest) {
        try {
            RegisterResponse response = userService.eliminarCuenta(id, deleteRequest.getPassword());
            
            if (response.isSuccess()) {
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new RegisterResponse(false, "Error al eliminar la cuenta: " + e.getMessage()));
        }
    }
}