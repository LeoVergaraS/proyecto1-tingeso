package com.app.proyecto1tingeso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.proyecto1tingeso.entities.HoraExtraEntity;

@Repository
public interface HoraExtraRepository extends JpaRepository<HoraExtraEntity, Long> {
}
