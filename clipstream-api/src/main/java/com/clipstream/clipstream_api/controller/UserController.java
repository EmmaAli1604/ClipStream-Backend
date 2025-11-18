package com.clipstream.clipstream_api.controller;

import com.clipstream.clipstream_api.dto.RegisterRequest;
import com.clipstream.clipstream_api.dto.RegisterResponse;
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

    // Puedes agregar más endpoints relacionados con usuarios
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        // Lógica para obtener usuario por ID
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody RegisterRequest updateRequest) {
        // Lógica para actualizar usuario
        return ResponseEntity.ok().build();
    }
}