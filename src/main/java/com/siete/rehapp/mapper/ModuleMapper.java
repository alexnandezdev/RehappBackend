package com.siete.rehapp.mapper;

import com.siete.rehapp.dto.ModuleDTO;
import com.siete.rehapp.entity.ModuleEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ModuleMapper {

    ModuleDTO toModuloDTO(ModuleEntity moduleEntity);
    ModuleEntity toModuleEntity(ModuleDTO moduloDTO);

}
