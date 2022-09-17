package com.app.proyecto1tingeso.repositories;

import com.app.proyecto1tingeso.entities.InasistenciaEntity;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InasistenciaRepository extends JpaRepository<InasistenciaEntity, Long> {
    @Transactional
    @Modifying
    @Query(value = "UPDATE inasistencias i SET i.dias_justificados = :d WHERE i.id = :id",
            nativeQuery = true)
    public void updateInasistenciaByJustificados(@Param("d") int d, @Param("id") long id);
}
