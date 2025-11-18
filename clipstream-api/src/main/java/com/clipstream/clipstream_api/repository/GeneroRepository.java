package com.clipstream.clipstream_api.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.clipstream.clipstream_api.model.Genero;

public interface GeneroRepository extends JpaRepository<Genero, Long> {
    List<Genero> findAllByOrderByNombreGeneroAsc();
}