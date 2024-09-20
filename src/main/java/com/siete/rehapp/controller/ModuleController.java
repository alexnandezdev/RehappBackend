package com.siete.rehapp.controller;

import com.siete.rehapp.dto.ModuleDTO;
import com.siete.rehapp.service.ModuleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/rehapp/module")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    @GetMapping("/getAllModules")
    public ResponseEntity<List<ModuleDTO>> getAllModules() {
        log.info("Getting all modules");
        List<ModuleDTO> modules = moduleService.getAllModules();
        log.info("Modules fetched successfully");
        return ResponseEntity.ok(modules);
    }

}
