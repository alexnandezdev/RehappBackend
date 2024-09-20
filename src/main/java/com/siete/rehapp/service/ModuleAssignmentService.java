package com.siete.rehapp.service;

import com.siete.rehapp.dto.ModuleAssignmentDTO;
import com.siete.rehapp.dto.ModuleDTO;
import com.siete.rehapp.entity.ModuleAssignmentEntity;
import com.siete.rehapp.exception.UserException;
import com.siete.rehapp.mapper.ModuleAssignmentMapper;
import com.siete.rehapp.mapper.ModuleMapper;
import com.siete.rehapp.repository.ModuleAssignmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ModuleAssignmentService {

    @Autowired
    private ModuleAssignmentRepository moduleAssignmentRepository;

    @Autowired
    private ModuleAssignmentMapper moduleAssignmentMapper;

    @Autowired
    private ModuleMapper moduleMapper;

    public ModuleAssignmentDTO assignModuleToPatient(ModuleAssignmentDTO moduleAssignmentDTO) {
        try {
            log.info("Checking if module {} is already assigned to patient {}",
                    moduleAssignmentDTO.getModuleId(), moduleAssignmentDTO.getPatientId());

            moduleAssignmentRepository.findByPatientUserIdAndModuleIdModuleId(
                            moduleAssignmentDTO.getPatientId(), moduleAssignmentDTO.getModuleId())
                    .ifPresent(existingAssignment -> {
                        throw new UserException("This module is already assigned to the patient.");
                    });

            log.info("Assigning module {} to patient {}", moduleAssignmentDTO.getModuleId(), moduleAssignmentDTO.getPatientId());

            ModuleAssignmentEntity moduleAssignmentEntity = moduleAssignmentMapper.toModuleAssignmentEntity(moduleAssignmentDTO);
            ModuleAssignmentEntity savedEntity = moduleAssignmentRepository.save(moduleAssignmentEntity);

            log.info("Module {} assigned to patient {} successfully", moduleAssignmentDTO.getModuleId(), moduleAssignmentDTO.getPatientId());
            return moduleAssignmentMapper.toModuleAssignmentDTO(savedEntity);

        } catch (UserException e) {
            log.error("Assignment error: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Unexpected error during module assignment", e);
            throw new UserException("An unexpected error occurred while assigning the module.");
        }
    }

    public List<ModuleDTO> getAssignedModulesByUserId(Long userId) {
        try {
            log.info("Fetching assigned modules for user with ID: {}", userId);

            List<ModuleAssignmentEntity> assignments = moduleAssignmentRepository.findByPatientUserId(userId);

            if (assignments.isEmpty()) {
                log.warn("No modules found for user with ID: {}", userId);
                throw new UserException("No modules assigned to this user.");
            }

            List<ModuleDTO> modules = assignments.stream()
                    .map(assignment -> moduleMapper.toModuloDTO(assignment.getModuleId()))
                    .collect(Collectors.toList());

            log.info("Found {} modules for user with ID: {}", modules.size(), userId);
            return modules;

        } catch (Exception e) {
            log.error("Error fetching modules for user with ID: {}", userId, e);
            throw new UserException("An error occurred while fetching assigned modules.");
        }
    }

}
