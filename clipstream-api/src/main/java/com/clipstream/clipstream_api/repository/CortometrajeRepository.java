package com.clipstream.clipstream_api.repository;

import com.clipstream.clipstream_api.model.Cortometraje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CortometrajeRepository extends JpaRepository<Cortometraje, Long> {
    
    // Buscar cortometrajes por género
    List<Cortometraje> findByGeneroId(Long generoId);
    
    // Buscar cortometrajes por usuario
    List<Cortometraje> findByUsuarioId(Long usuarioId);
    
    // Buscar cortometrajes por nombre (búsqueda parcial)
    List<Cortometraje> findByNombreContainingIgnoreCase(String nombre);
    
    // Buscar cortometrajes por director
    List<Cortometraje> findByDirectorContainingIgnoreCase(String director);
    
    // Obtener cortometrajes ordenados por fecha descendente (más recientes primero)
    List<Cortometraje> findAllByOrderByFechaDesc();
    
    // Obtener cortometrajes ordenados por calificación descendente
    List<Cortometraje> findAllByOrderByCalificacionDesc();
    
    // Buscar por género y nombre
    List<Cortometraje> findByGeneroIdAndNombreContainingIgnoreCase(Long generoId, String nombre);
}