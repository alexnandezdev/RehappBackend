package com.siete.rehapp.repository;

import com.siete.rehapp.entity.ModuleAssignmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModuleAssignmentRepository extends JpaRepository<ModuleAssignmentEntity, Long> {

    Optional<ModuleAssignmentEntity> findByPatientUserIdAndModuleIdModuleId(Long patientId, Long moduleId);

    List<ModuleAssignmentEntity> findByPatientUserId(Long patientId);

}
