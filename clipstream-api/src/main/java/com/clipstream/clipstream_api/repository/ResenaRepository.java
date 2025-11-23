package com.clipstream.clipstream_api.repository;

import com.clipstream.clipstream_api.model.Resena;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ResenaRepository extends JpaRepository<Resena, Long> {
    
    // Encontrar reseñas por cortometraje
    List<Resena> findByCortometrajeId(Long cortometrajeId);
    
    // Contar reseñas por cortometraje
    Long countByCortometrajeId(Long cortometrajeId);
    
    // Incrementar likes
    @Modifying
    @Query("UPDATE Resena r SET r.likes = r.likes + 1 WHERE r.resenaId = :resenaId")
    int incrementLikes(@Param("resenaId") Long resenaId);
    
    // Verificar si usuario ya tiene reseña en el cortometraje
    @Query("SELECT COUNT(r) > 0 FROM Resena r WHERE r.usuarioId = :usuarioId AND r.cortometrajeId = :cortometrajeId")
    boolean existsByUsuarioIdAndCortometrajeId(@Param("usuarioId") Long usuarioId, @Param("cortometrajeId") Long cortometrajeId);
}