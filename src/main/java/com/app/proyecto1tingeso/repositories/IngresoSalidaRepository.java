package com.app.proyecto1tingeso.repositories;

import com.app.proyecto1tingeso.entities.IngresoSalidaEntity;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IngresoSalidaRepository extends JpaRepository<IngresoSalidaEntity, Long> {
    @Query(value = "SELECT * FROM ingresos_salidas i WHERE i.hora > '18:00' ", nativeQuery = true)
    public ArrayList<IngresoSalidaEntity> findSalidas();

}
