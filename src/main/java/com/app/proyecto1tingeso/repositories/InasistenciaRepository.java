package com.app.proyecto1tingeso.repositories;

import com.app.proyecto1tingeso.entities.InasistenciaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InasistenciaRepository extends JpaRepository<InasistenciaEntity, Long> {
    public InasistenciaEntity findByRut(String rut);
}
