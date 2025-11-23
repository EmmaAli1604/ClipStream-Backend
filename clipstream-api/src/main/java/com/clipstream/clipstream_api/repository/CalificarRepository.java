package com.clipstream.clipstream_api.repository;

import com.clipstream.clipstream_api.model.Calificar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CalificarRepository extends JpaRepository<Calificar, Long> {
    
    @Query("SELECT c FROM Calificar c WHERE c.usuarioId = :usuarioId AND c.cortometrajeId = :cortometrajeId")
    Optional<Calificar> findByUsuarioIdAndCortometrajeId(@Param("usuarioId") Long usuarioId, 
                                                         @Param("cortometrajeId") Long cortometrajeId);
    
    @Query("SELECT COUNT(c) FROM Calificar c WHERE c.cortometrajeId = :cortometrajeId")
    Long countByCortometrajeId(@Param("cortometrajeId") Long cortometrajeId);
}