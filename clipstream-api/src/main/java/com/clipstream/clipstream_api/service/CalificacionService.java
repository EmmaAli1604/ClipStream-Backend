package com.clipstream.clipstream_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CalificacionService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> calificarCortometraje(Long usuarioId, Long cortometrajeId, Double puntuacion) {
        Map<String, Object> response = new HashMap<>();
        
        // Validar puntuación
        if (puntuacion < 0 || puntuacion > 5) {
            throw new RuntimeException("La puntuación debe estar entre 0 y 5");
        }

        // Verificar si ya existe una calificación
        String checkSql = "SELECT COUNT(*) FROM Calificar WHERE UsuarioID = ? AND CortometrajeID = ?";
        Integer count = jdbcTemplate.queryForObject(checkSql, Integer.class, usuarioId, cortometrajeId);

        if (count > 0) {
            // Actualizar calificación existente
            String updateSql = "UPDATE Calificar SET Puntuacion = ? WHERE UsuarioID = ? AND CortometrajeID = ?";
            jdbcTemplate.update(updateSql, puntuacion, usuarioId, cortometrajeId);
            response.put("message", "Calificación actualizada");
        } else {
            // Insertar nueva calificación
            String insertSql = "INSERT INTO Calificar (UsuarioID, CortometrajeID, Puntuacion) VALUES (?, ?, ?)";
            jdbcTemplate.update(insertSql, usuarioId, cortometrajeId, puntuacion);
            response.put("message", "Calificación guardada");
        }

        response.put("success", true);
        response.put("puntuacion", puntuacion);
        return response;
    }

    public Double getCalificacionUsuario(Long usuarioId, Long cortometrajeId) {
        String sql = "SELECT Puntuacion FROM Calificar WHERE UsuarioID = ? AND CortometrajeID = ?";
        try {
            return jdbcTemplate.queryForObject(sql, Double.class, usuarioId, cortometrajeId);
        } catch (Exception e) {
            return null; // No hay calificación
        }
    }

    public Double getPromedioCalificacion(Long cortometrajeId) {
        String sql = "SELECT Calificacion FROM Cortometraje WHERE CortometrajeID = ?";
        try {
            return jdbcTemplate.queryForObject(sql, Double.class, cortometrajeId);
        } catch (Exception e) {
            return 0.0;
        }
    }
}