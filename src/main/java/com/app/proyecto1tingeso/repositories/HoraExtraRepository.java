package com.app.proyecto1tingeso.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.proyecto1tingeso.entities.HoraExtraEntity;

@Repository
public interface HoraExtraRepository extends JpaRepository<HoraExtraEntity, Long> {
    @Transactional
    @Modifying
    @Query(value = "UPDATE horas_extras h SET h.autorizado = :a WHERE h.id = :id",
            nativeQuery = true)
    public void updateHorasExtrasByAutorizados(@Param("a") int a, @Param("id") long id);

    @Query(value = "select * from horas_extras h where h.mes = :mes and h.anio = :anio and h.rut_empleado = :rut", 
            nativeQuery = true)
    public HoraExtraEntity findHoraExtraEmpleadoByFecha(@Param("mes") int mes, @Param("anio") int anio, @Param("rut") String rut);
}
