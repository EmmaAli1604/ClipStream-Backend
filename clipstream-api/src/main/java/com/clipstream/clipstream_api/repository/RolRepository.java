package com.clipstream.clipstream_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.clipstream.clipstream_api.model.Rol;

public interface RolRepository extends JpaRepository<Rol, Integer> {
    
}
