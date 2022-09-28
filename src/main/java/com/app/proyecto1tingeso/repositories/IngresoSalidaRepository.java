package com.app.proyecto1tingeso.repositories;

import com.app.proyecto1tingeso.entities.IngresoSalidaEntity;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IngresoSalidaRepository extends JpaRepository<IngresoSalidaEntity, Long> {
    @Query(value = "SELECT * FROM ingresos_salidas i WHERE i.hora > '18:00' ", nativeQuery = true)
    public ArrayList<IngresoSalidaEntity> findSalidas();

    @Query(value = "SELECT count(*) FROM ingresos_salidas i WHERE i.hora > '8:10' and '8:25' >= i.hora and i.rut_empleado = :rut", nativeQuery = true)
    public int findAtrasosUno(@Param("rut") String rut);

    @Query(value = "SELECT count(*) FROM ingresos_salidas i WHERE i.hora > '8:25' and '8:45' >= i.hora and i.rut_empleado = :rut", nativeQuery = true)
    public int findAtrasosDos(@Param("rut") String rut);

    @Query(value = "SELECT count(*) FROM ingresos_salidas i WHERE i.hora > '8:45' and '9:10' >= i.hora and i.rut_empleado = :rut", nativeQuery = true)
    public int findAtrasosTres(@Param("rut") String rut);

    @Query(value = "SELECT count(*) FROM ingresos_salidas i WHERE i.hora > '9:10' and '18:00' > i.hora and i.rut_empleado = :rut", nativeQuery = true)
    public int findAtrasosCuatro(@Param("rut") String rut);

    @Query(value = "SELECT * FROM ingresos_salidas i WHERE i.hora > '9:10' and '18:00' > i.hora", nativeQuery = true)
    public ArrayList<IngresoSalidaEntity> findInasistencias();

}
