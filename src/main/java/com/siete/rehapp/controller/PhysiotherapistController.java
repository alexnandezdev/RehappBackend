package com.siete.rehapp.controller;

import com.siete.rehapp.dto.AssignPatientDTO;
import com.siete.rehapp.dto.PhysioUserRegisterDTO;
import com.siete.rehapp.service.PhysiotherapistService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/rehapp/physiotherapist")
public class PhysiotherapistController {

    @Autowired
    private PhysiotherapistService physiotherapistService;

    @PostMapping("/register")
    public ResponseEntity<PhysioUserRegisterDTO> registerPhysiotherapist(@RequestBody PhysioUserRegisterDTO physioUserRegisterDTO) {
        log.info("Registering physiotherapist: {}", physioUserRegisterDTO);
        PhysioUserRegisterDTO registeredPhysio = physiotherapistService.registerPhysiotherapist(physioUserRegisterDTO);
        log.info("Physiotherapist registered successfully: {}", registeredPhysio);
        return ResponseEntity.ok(registeredPhysio);
    }

    @PostMapping("/assign-patient")
    public ResponseEntity<String> assignPatientToPhysiotherapist(@RequestBody AssignPatientDTO assignPatientDTO) {
        physiotherapistService.assignPatient(assignPatientDTO.getPhysiotherapistId(), assignPatientDTO.getPatientId());
        return ResponseEntity.ok("Patient assigned to physiotherapist successfully");
    }
}
