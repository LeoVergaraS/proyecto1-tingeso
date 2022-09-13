package com.app.proyecto1tingeso.repositories;

import com.app.proyecto1tingeso.entities.EmpleadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<EmpleadoEntity, Long> {
    public EmpleadoEntity findByRut(String rut);
}
