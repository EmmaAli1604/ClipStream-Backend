package com.clipstream.clipstream_api.controller;

import com.clipstream.clipstream_api.dto.CortometrajeRequest;
import com.clipstream.clipstream_api.dto.CortometrajeResponse;
import com.clipstream.clipstream_api.model.Cortometraje;
import com.clipstream.clipstream_api.service.CortometrajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cortometrajes")
@CrossOrigin(origins = "http://localhost:5173")
public class CortometrajeController {

    @Autowired
    private CortometrajeService cortometrajeService;

    // GET - Obtener todos los cortometrajes
    @GetMapping
    public ResponseEntity<CortometrajeResponse> obtenerTodosLosCortometrajes() {
        List<Cortometraje> cortometrajes = cortometrajeService.obtenerTodosLosCortometrajes();
        return ResponseEntity.ok(new CortometrajeResponse(true, "Cortometrajes obtenidos exitosamente", cortometrajes));
    }

    // GET - Obtener cortometraje por ID
    @GetMapping("/{id}")
    public ResponseEntity<CortometrajeResponse> obtenerCortometrajePorId(@PathVariable Long id) {
        Optional<Cortometraje> cortometraje = cortometrajeService.obtenerCortometrajePorId(id);
        if (cortometraje.isPresent()) {
            return ResponseEntity.ok(new CortometrajeResponse(true, "Cortometraje encontrado", cortometraje.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // POST - Crear nuevo cortometraje
    @PostMapping
    public ResponseEntity<CortometrajeResponse> crearCortometraje(@RequestBody CortometrajeRequest request) {
        try {
            Cortometraje nuevoCortometraje = new Cortometraje();
            nuevoCortometraje.setUsuarioId(request.getUsuarioId());
            nuevoCortometraje.setGeneroId(request.getGeneroId());
            nuevoCortometraje.setNombre(request.getNombre());
            nuevoCortometraje.setSinopsis(request.getSinopsis());
            nuevoCortometraje.setFecha(request.getFecha());
            nuevoCortometraje.setFoto(request.getFoto());
            nuevoCortometraje.setVideo(request.getVideo());
            nuevoCortometraje.setDirector(request.getDirector());
            nuevoCortometraje.setNumVistas(request.getNumVistas());
            nuevoCortometraje.setCalificacion(request.getCalificacion());

            Cortometraje cortometrajeGuardado = cortometrajeService.crearCortometraje(nuevoCortometraje);
            return ResponseEntity.ok(new CortometrajeResponse(true, "Cortometraje creado exitosamente", cortometrajeGuardado));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new CortometrajeResponse(false, "Error al crear el cortometraje: " + e.getMessage()));
        }
    }

    // PUT - Actualizar cortometraje
    @PutMapping("/{id}")
    public ResponseEntity<CortometrajeResponse> actualizarCortometraje(@PathVariable Long id, @RequestBody CortometrajeRequest request) {
        try {
            Cortometraje cortometrajeActualizado = new Cortometraje();
            cortometrajeActualizado.setNombre(request.getNombre());
            cortometrajeActualizado.setSinopsis(request.getSinopsis());
            cortometrajeActualizado.setGeneroId(request.getGeneroId());
            cortometrajeActualizado.setFecha(request.getFecha());
            cortometrajeActualizado.setFoto(request.getFoto());
            cortometrajeActualizado.setVideo(request.getVideo());
            cortometrajeActualizado.setDirector(request.getDirector());
            cortometrajeActualizado.setNumVistas(request.getNumVistas());
            cortometrajeActualizado.setCalificacion(request.getCalificacion());

            Optional<Cortometraje> resultado = cortometrajeService.actualizarCortometraje(id, cortometrajeActualizado);
            if (resultado.isPresent()) {
                return ResponseEntity.ok(new CortometrajeResponse(true, "Cortometraje actualizado exitosamente", resultado.get()));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new CortometrajeResponse(false, "Error al actualizar el cortometraje: " + e.getMessage()));
        }
    }

    // DELETE - Eliminar cortometraje
    @DeleteMapping("/{id}")
    public ResponseEntity<CortometrajeResponse> eliminarCortometraje(@PathVariable Long id) {
        boolean eliminado = cortometrajeService.eliminarCortometraje(id);
        if (eliminado) {
            return ResponseEntity.ok(new CortometrajeResponse(true, "Cortometraje eliminado exitosamente"));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // GET - Buscar cortometrajes por género
    @GetMapping("/genero/{generoId}")
    public ResponseEntity<CortometrajeResponse> buscarPorGenero(@PathVariable Long generoId) {
        List<Cortometraje> cortometrajes = cortometrajeService.buscarPorGenero(generoId);
        return ResponseEntity.ok(new CortometrajeResponse(true, "Cortometrajes del género encontrados", cortometrajes));
    }

    // GET - Buscar cortometrajes por nombre
    @GetMapping("/buscar")
    public ResponseEntity<CortometrajeResponse> buscarPorNombre(@RequestParam String nombre) {
        List<Cortometraje> cortometrajes = cortometrajeService.buscarPorNombre(nombre);
        return ResponseEntity.ok(new CortometrajeResponse(true, "Cortometrajes encontrados", cortometrajes));
    }

    // GET - Obtener cortometrajes por usuario
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<CortometrajeResponse> obtenerPorUsuario(@PathVariable Long usuarioId) {
        List<Cortometraje> cortometrajes = cortometrajeService.buscarPorUsuario(usuarioId);
        return ResponseEntity.ok(new CortometrajeResponse(true, "Cortometrajes del usuario encontrados", cortometrajes));
    }

    // GET - Obtener cortometrajes populares
    @GetMapping("/populares")
    public ResponseEntity<CortometrajeResponse> obtenerPopulares() {
        List<Cortometraje> cortometrajes = cortometrajeService.obtenerPopulares();
        return ResponseEntity.ok(new CortometrajeResponse(true, "Cortometrajes populares obtenidos", cortometrajes));
    }

    // PATCH - Incrementar vistas
    @PatchMapping("/{id}/vistas")
    public ResponseEntity<CortometrajeResponse> incrementarVistas(@PathVariable Long id) {
        cortometrajeService.incrementarVistas(id);
        return ResponseEntity.ok(new CortometrajeResponse(true, "Vistas incrementadas"));
    }


    // GET - Obtener cortometrajes mejor calificados
@GetMapping("/mejor-calificados")
public ResponseEntity<CortometrajeResponse> obtenerMejorCalificados() {
    List<Cortometraje> cortometrajes = cortometrajeService.obtenerMejorCalificados();
    return ResponseEntity.ok(new CortometrajeResponse(true, "Cortometrajes mejor calificados obtenidos", cortometrajes));
}

// GET - Obtener cortometrajes recién agregados
@GetMapping("/recientes")
public ResponseEntity<CortometrajeResponse> obtenerRecientes() {
    List<Cortometraje> cortometrajes = cortometrajeService.obtenerTodosLosCortometrajes();
    return ResponseEntity.ok(new CortometrajeResponse(true, "Cortometrajes recientes obtenidos", cortometrajes));
}
}