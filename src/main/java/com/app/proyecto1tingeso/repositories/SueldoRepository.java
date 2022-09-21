package com.app.proyecto1tingeso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.proyecto1tingeso.entities.SueldoEntity;

@Repository
public interface SueldoRepository extends JpaRepository<SueldoEntity, Long>{
}
