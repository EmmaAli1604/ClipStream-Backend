package com.clipstream.clipstream_api.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.clipstream.clipstream_api.model.Calificar;
import com.clipstream.clipstream_api.model.CalificarId;

public interface CalificarRepository extends JpaRepository<Calificar, CalificarId> {
}
