package com.app.proyecto1tingeso.repositories;

import com.app.proyecto1tingeso.entities.InasistenciaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InasistenciaRepository extends JpaRepository<InasistenciaEntity, Long> {
}
