package com.clipstream.clipstream_api.controller;

import com.clipstream.clipstream_api.service.CalificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/calificaciones")
@CrossOrigin(origins = "http://localhost:5173")
public class CalificacionController {

    @Autowired
    private CalificacionService calificacionService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> calificarCortometraje(
            @RequestParam Long usuarioId,
            @RequestParam Long cortometrajeId,
            @RequestParam Double puntuacion) {
        try {
            Map<String, Object> result = calificacionService.calificarCortometraje(usuarioId, cortometrajeId, puntuacion);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/usuario/{usuarioId}/cortometraje/{cortometrajeId}")
    public ResponseEntity<Map<String, Object>> getCalificacionUsuario(
            @PathVariable Long usuarioId,
            @PathVariable Long cortometrajeId) {
        try {
            Double calificacion = calificacionService.getCalificacionUsuario(usuarioId, cortometrajeId);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("calificacion", calificacion);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/cortometraje/{cortometrajeId}/promedio")
    public ResponseEntity<Map<String, Object>> getPromedioCalificacion(@PathVariable Long cortometrajeId) {
        try {
            Double promedio = calificacionService.getPromedioCalificacion(cortometrajeId);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("promedio", promedio);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}