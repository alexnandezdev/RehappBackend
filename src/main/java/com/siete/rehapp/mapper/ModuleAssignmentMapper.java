package com.siete.rehapp.mapper;

import com.siete.rehapp.dto.ModuleAssignmentDTO;
import com.siete.rehapp.entity.ModuleAssignmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ModuleAssignmentMapper {

    @Mapping(source = "physiotherapistId", target = "physiotherapist.userId")
    @Mapping(source = "patientId", target = "patient.userId")
    @Mapping(source = "moduleId", target = "moduleId.moduleId")
    ModuleAssignmentEntity toModuleAssignmentEntity(ModuleAssignmentDTO moduleAssignmentDTO);

    @Mapping(source = "physiotherapist.userId", target = "physiotherapistId")
    @Mapping(source = "patient.userId", target = "patientId")
    @Mapping(source = "moduleId.moduleId", target = "moduleId")
    ModuleAssignmentDTO toModuleAssignmentDTO(ModuleAssignmentEntity moduleAssignmentEntity);

}
