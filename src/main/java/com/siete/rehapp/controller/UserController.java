package com.siete.rehapp.controller;

import com.siete.rehapp.dto.PatientUserRegisterDTO;
import com.siete.rehapp.dto.UpdatePasswordDTO;
import com.siete.rehapp.dto.UserDTO;
import com.siete.rehapp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/rehapp/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public ResponseEntity<PatientUserRegisterDTO> createUser(@RequestBody PatientUserRegisterDTO userRegister) {
        log.info("Creating user: {}", userRegister);
        PatientUserRegisterDTO createdUser = userService.save(userRegister);
        log.info("Created user: {}", createdUser);
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        log.info("Fetching user with id: {}", id);
        UserDTO user = userService.getById(id);
        log.info("User fetched successfully: {}", user);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/update")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDto) {
        log.info("Updating user with id: {}", userDto.getUserId());
        UserDTO updatedUser = userService.updateUser(userDto);
        log.info("User updated successfully: {}", updatedUser);
        return ResponseEntity.ok(updatedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(
            @RequestParam String email,
            @RequestParam String password
    ){
        log.info("Logging in with email: {}", email);
        UserDTO user = userService.login(email, password);
        log.info("User logged in successfully: {}", user);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/updatePassword")
    public  ResponseEntity<String> updatePassword(@RequestBody UpdatePasswordDTO updatePasswordDTO){
        log.info("Updating password for email: {}", updatePasswordDTO.getEmail());
        String response = userService.updatePassword(updatePasswordDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/find-patient")
    public ResponseEntity<UserDTO> findPatientByIdentificationNumber(@RequestParam String identificationNumber) {
        log.info("Searching for patient with identification number: {}", identificationNumber);
        UserDTO patient = userService.findPatientByIdentificationNumber(identificationNumber);
        log.info("Patient found: {}", patient);
        return ResponseEntity.ok(patient);
    }

}
