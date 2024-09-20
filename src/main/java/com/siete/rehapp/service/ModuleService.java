package com.siete.rehapp.service;

import com.siete.rehapp.dto.ModuleDTO;
import com.siete.rehapp.entity.ModuleEntity;
import com.siete.rehapp.exception.ModuleException;
import com.siete.rehapp.mapper.ModuleMapper;
import com.siete.rehapp.repository.ModuleRespository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ModuleService {

    @Autowired
    private ModuleRespository moduleRespository;

    @Autowired
    private ModuleMapper moduleMapper;

    public List<ModuleDTO> getAllModules() {
        try {
            log.info("Loading all modules");
            List<ModuleEntity> modules = moduleRespository.findAll();
            return modules.stream()
                    .map(moduleMapper::toModuloDTO)
                    .collect(Collectors.toList());
        }catch (Exception e) {
            log.error("Failed to get all modules:", e);
            throw new ModuleException("Failed to get all modules: " + e.getMessage(), e);
        }
    }

}
