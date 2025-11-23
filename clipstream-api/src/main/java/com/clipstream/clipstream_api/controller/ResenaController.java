package com.clipstream.clipstream_api.controller;

import com.clipstream.clipstream_api.model.Resena;
import com.clipstream.clipstream_api.service.ResenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/resenas")
@CrossOrigin(origins = "http://localhost:5173")
public class ResenaController {

    @Autowired
    private ResenaService resenaService;

    @GetMapping("/cortometraje/{cortometrajeId}")
    public ResponseEntity<Map<String, Object>> getResenasByCortometraje(@PathVariable Long cortometrajeId) {
        try {
            List<Resena> resenas = resenaService.getResenasByCortometraje(cortometrajeId);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("data", resenas);
            response.put("count", resenas.size());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Error al obtener reseñas: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createResena(@RequestBody Resena resena) {
        try {
            Resena nuevaResena = resenaService.createResena(resena);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("data", nuevaResena);
            response.put("message", "Reseña creada exitosamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/{resenaId}/like")
    public ResponseEntity<Map<String, Object>> likeResena(@PathVariable Long resenaId) {
        try {
            boolean success = resenaService.likeResena(resenaId);
            Map<String, Object> response = new HashMap<>();
            
            if (success) {
                response.put("success", true);
                response.put("message", "Like agregado");
            } else {
                response.put("success", false);
                response.put("message", "No se pudo agregar el like");
            }
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Error al dar like: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getResenaById(@PathVariable Long id) {
        try {
            Optional<Resena> resena = resenaService.getResenaById(id);
            Map<String, Object> response = new HashMap<>();
            
            if (resena.isPresent()) {
                response.put("success", true);
                response.put("data", resena.get());
            } else {
                response.put("success", false);
                response.put("message", "Reseña no encontrada");
            }
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Error al obtener reseña: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteResena(@PathVariable Long id) {
        try {
            resenaService.deleteResena(id);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Reseña eliminada exitosamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Error al eliminar reseña: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}