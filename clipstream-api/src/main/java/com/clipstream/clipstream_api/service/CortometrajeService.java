package com.clipstream.clipstream_api.service;

import com.clipstream.clipstream_api.model.Cortometraje;
import com.clipstream.clipstream_api.repository.CortometrajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CortometrajeService {

    @Autowired
    private CortometrajeRepository cortometrajeRepository;

    // Obtener todos los cortometrajes
    public List<Cortometraje> obtenerTodosLosCortometrajes() {
        return cortometrajeRepository.findAllByOrderByFechaDesc();
    }

    // Obtener cortometraje por ID
    public Optional<Cortometraje> obtenerCortometrajePorId(Long id) {
        return cortometrajeRepository.findById(id);
    }

    // Crear nuevo cortometraje
    public Cortometraje crearCortometraje(Cortometraje cortometraje) {
        // Establecer valores por defecto si son nulos
        if (cortometraje.getNumVistas() == null) {
            cortometraje.setNumVistas(0);
        }
        if (cortometraje.getCalificacion() == null) {
            cortometraje.setCalificacion(0.0);
        }
        if (cortometraje.getFecha() == null) {
            cortometraje.setFecha(LocalDate.now());
        }
        
        return cortometrajeRepository.save(cortometraje);
    }

    // Actualizar cortometraje
    public Optional<Cortometraje> actualizarCortometraje(Long id, Cortometraje cortometrajeActualizado) {
        return cortometrajeRepository.findById(id).map(cortometraje -> {
            cortometraje.setNombre(cortometrajeActualizado.getNombre());
            cortometraje.setSinopsis(cortometrajeActualizado.getSinopsis());
            cortometraje.setGeneroId(cortometrajeActualizado.getGeneroId());
            cortometraje.setFecha(cortometrajeActualizado.getFecha());
            cortometraje.setFoto(cortometrajeActualizado.getFoto());
            cortometraje.setVideo(cortometrajeActualizado.getVideo());
            cortometraje.setDirector(cortometrajeActualizado.getDirector());
            cortometraje.setNumVistas(cortometrajeActualizado.getNumVistas());
            cortometraje.setCalificacion(cortometrajeActualizado.getCalificacion());
            return cortometrajeRepository.save(cortometraje);
        });
    }

    // Eliminar cortometraje
    public boolean eliminarCortometraje(Long id) {
        if (cortometrajeRepository.existsById(id)) {
            cortometrajeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Buscar cortometrajes por género
    public List<Cortometraje> buscarPorGenero(Long generoId) {
        return cortometrajeRepository.findByGeneroId(generoId);
    }

    // Buscar cortometrajes por nombre
    public List<Cortometraje> buscarPorNombre(String nombre) {
        return cortometrajeRepository.findByNombreContainingIgnoreCase(nombre);
    }

    // Buscar cortometrajes por director
    public List<Cortometraje> buscarPorDirector(String director) {
        return cortometrajeRepository.findByDirectorContainingIgnoreCase(director);
    }

    // Buscar cortometrajes por usuario
    public List<Cortometraje> buscarPorUsuario(Long usuarioId) {
        return cortometrajeRepository.findByUsuarioId(usuarioId);
    }

    // Obtener cortometrajes más populares (por vistas)
    public List<Cortometraje> obtenerPopulares() {
        return cortometrajeRepository.findAll().stream()
                .sorted((c1, c2) -> c2.getNumVistas().compareTo(c1.getNumVistas()))
                .limit(10)
                .toList();
    }

    // Incrementar número de vistas
    public void incrementarVistas(Long cortometrajeId) {
        cortometrajeRepository.findById(cortometrajeId).ifPresent(cortometraje -> {
            cortometraje.setNumVistas(cortometraje.getNumVistas() + 1);
            cortometrajeRepository.save(cortometraje);
        });
    }
}