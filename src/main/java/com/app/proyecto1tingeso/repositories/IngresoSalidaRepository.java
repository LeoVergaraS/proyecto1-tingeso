package com.app.proyecto1tingeso.repositories;

import com.app.proyecto1tingeso.entities.IngresoSalidaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface IngresoSalidaRepository extends JpaRepository<IngresoSalidaEntity, Long> {
}
