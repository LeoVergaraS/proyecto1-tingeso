package com.app.proyecto1tingeso.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.proyecto1tingeso.entities.SueldoEntity;

@Repository
public interface SueldoRepository extends JpaRepository<SueldoEntity, Long>{
    @Query(value = "select * from sueldo s where mes = :mes and anio = :anio",
            nativeQuery = true)
    public ArrayList<SueldoEntity> findSueldosbyFecha(@Param("mes") int mes, @Param("anio") int anio);
}
