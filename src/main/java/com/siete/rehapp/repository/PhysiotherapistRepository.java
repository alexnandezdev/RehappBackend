package com.siete.rehapp.repository;

import com.siete.rehapp.entity.PhysiotherapistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhysiotherapistRepository extends JpaRepository<PhysiotherapistEntity, Long> {
}
