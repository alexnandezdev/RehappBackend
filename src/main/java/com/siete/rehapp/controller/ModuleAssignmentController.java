package com.siete.rehapp.controller;

import com.siete.rehapp.dto.ModuleAssignmentDTO;
import com.siete.rehapp.dto.ModuleDTO;
import com.siete.rehapp.service.ModuleAssignmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/rehapp/module-assignment")
public class ModuleAssignmentController {

    @Autowired
    private ModuleAssignmentService moduleAssignmentService;

    @PostMapping("/assign")
    public ResponseEntity<ModuleAssignmentDTO> assignModuleToPatient(@RequestBody ModuleAssignmentDTO moduleAssignmentDTO) {
        log.info("Received request to assign module {} to patient {}",
                moduleAssignmentDTO.getModuleId(), moduleAssignmentDTO.getPatientId());

        ModuleAssignmentDTO assignedModule = moduleAssignmentService.assignModuleToPatient(moduleAssignmentDTO);

        log.info("Module {} assigned to patient {} successfully",
                moduleAssignmentDTO.getModuleId(), moduleAssignmentDTO.getPatientId());

        return ResponseEntity.ok(assignedModule);
    }

    @GetMapping("/user/{userId}/modules")
    public ResponseEntity<List<ModuleDTO>> getAssignedModulesByUserId(@PathVariable Long userId) {
        log.info("Received request to get assigned modules for user ID: {}", userId);

        List<ModuleDTO> assignedModules = moduleAssignmentService.getAssignedModulesByUserId(userId);

        log.info("Returning {} assigned modules for user ID: {}", assignedModules.size(), userId);
        return ResponseEntity.ok(assignedModules);
    }

}
