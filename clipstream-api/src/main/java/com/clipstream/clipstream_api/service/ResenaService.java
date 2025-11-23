package com.clipstream.clipstream_api.service;

import com.clipstream.clipstream_api.model.Resena;
import com.clipstream.clipstream_api.model.Usuario;
import com.clipstream.clipstream_api.repository.ResenaRepository;
import com.clipstream.clipstream_api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ResenaService {

    @Autowired
    private ResenaRepository resenaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository; // Agregar este repositorio

    public List<Resena> getResenasByCortometraje(Long cortometrajeId) {
        List<Resena> resenas = resenaRepository.findByCortometrajeId(cortometrajeId);
        
        // Enriquecer con información real del usuario
        for (Resena resena : resenas) {
            Optional<Usuario> usuarioOpt = usuarioRepository.findById(resena.getUsuarioId());
            if (usuarioOpt.isPresent()) {
                Usuario usuario = usuarioOpt.get();
                resena.setUsuarioNombre(usuario.getNombre() + " " + usuario.getApellido());
            } else {
                resena.setUsuarioNombre("Usuario Anónimo");
            }
        }
        
        return resenas;
    }

    public Resena createResena(Resena resena) {
        // Validar que el usuario existe
        if (!usuarioRepository.existsById(resena.getUsuarioId())) {
            throw new RuntimeException("Usuario no encontrado");
        }

        // Validar que el usuario no tenga ya una reseña para este cortometraje
        boolean exists = resenaRepository.existsByUsuarioIdAndCortometrajeId(
            resena.getUsuarioId(), resena.getCortometrajeId());
        
        if (exists) {
            throw new RuntimeException("Ya has escrito una reseña para este cortometraje");
        }
        
        return resenaRepository.save(resena);
    }
    
    public Optional<Resena> getResenaById(Long id) {
        return resenaRepository.findById(id);
    }

    public boolean likeResena(Long resenaId) {
        try {
            int updated = resenaRepository.incrementLikes(resenaId);
            return updated > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public void deleteResena(Long id) {
        resenaRepository.deleteById(id);
    }

    public Long countResenasByCortometraje(Long cortometrajeId) {
        return resenaRepository.countByCortometrajeId(cortometrajeId);
    }
}