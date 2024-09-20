package com.siete.rehapp.service;

import com.siete.rehapp.dto.PatientUserRegisterDTO;
import com.siete.rehapp.dto.UpdatePasswordDTO;
import com.siete.rehapp.dto.UserDTO;
import com.siete.rehapp.entity.PhysiotherapistEntity;
import com.siete.rehapp.entity.UserEntity;
import com.siete.rehapp.enums.UserType;
import com.siete.rehapp.exception.UserException;
import com.siete.rehapp.mapper.UserMapper;
import com.siete.rehapp.repository.UserRepository;
import com.siete.rehapp.utils.Base64Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper userMapper;

    public PatientUserRegisterDTO save(PatientUserRegisterDTO patientUserRegisterDto) {
        try {

            String encodedPassword = Base64Util.encode(patientUserRegisterDto.getPassword());
            patientUserRegisterDto.setPassword(encodedPassword);

            return userMapper.toUserRegisterDTO(
                    userRepository.save(userMapper.toUserEntityRegister(patientUserRegisterDto))
            );
        } catch (Exception e) {
            log.error("Error saving user: ", e);
            throw new UserException("Error saving user: " + e.getMessage());
        }
    }

    public UserDTO getById(Long userId) {
        try {
            UserEntity userEntity = userRepository.findById(userId)
                    .orElseThrow(() -> new UserException("User not found"));
            return userMapper.toUserDTO(userEntity);
        } catch (Exception e) {
            log.error("Error getting user: ", e);
            throw new UserException("Error getting user: " + e.getMessage());
        }
    }

    public UserDTO updateUser(UserDTO userDTO) {
        try {
            log.info("Updating user: " + userDTO);

            UserEntity existingUserEntity = userRepository.findById(userDTO.getUserId())
                    .orElseThrow(() -> new UserException("User not found"));
            log.info("Found existing user: {}", existingUserEntity);

            //userDTO.setPassword(Base64Util.encode(userDTO.getPassword()));
            log.info("Modify user: {}", userDTO);

            userMapper.updateUserFromDto(userDTO, existingUserEntity);
            log.info("Mapper user: {}", existingUserEntity);

            UserEntity updatedUserEntity = userRepository.save(existingUserEntity);
            return userMapper.toUserDTO(updatedUserEntity);
        } catch (Exception e) {
            log.error("Error updating user: ", e);
            throw new UserException("Error updating user: " + e.getMessage());
        }
    }

    public UserDTO login(String email, String password) {
        try {
            log.info("Start login");
            UserEntity userEntity = userRepository.findByEmail(email)
                    .orElseThrow(() -> new UserException("Invalid email or password"));
            log.info("User found: {}", userEntity);

            String encodedPassword = Base64Util.encode(password);
            if (!userEntity.getPassword().equals(encodedPassword)) {
                throw new UserException("Invalid email or password");
            }

            UserDTO userDTO = userMapper.toUserDTO(userEntity);

            // Si el usuario es fisioterapeuta, obten la tarjeta profesional
            if (userEntity.getUserType() == UserType.PHYSIOTHERAPIST) {
                PhysiotherapistEntity physiotherapistEntity = userRepository.findPhysiotherapistByUserId(userEntity.getUserId())
                        .orElseThrow(() -> new UserException("Fisioterapeuta no encontrado para este usuario"));
                userDTO.setProfessionalCardNumber(physiotherapistEntity.getProfessionalCardNumber());
            }

            return userDTO;
        } catch (Exception e) {
            log.error("Error during login: ", e);
            throw new UserException("Error during login: " + e.getMessage());
        }
    }

    public String updatePassword(UpdatePasswordDTO updatePasswordDTO){
        try{
            log.info("Updating password for email: " + updatePasswordDTO.getEmail());
            UserEntity userEntity = userRepository.findByEmail(updatePasswordDTO.getEmail())
                    .orElseThrow(() -> new UserException("User not found"));

            String encodedPassword = Base64Util.encode(updatePasswordDTO.getNewPassword());
            userEntity.setPassword(encodedPassword);

            userRepository.save(userEntity);

            log.info("Password updated successfully for email: " + updatePasswordDTO.getEmail());
            return "User update successfully";
        }catch (Exception e){
            log.error("Error updating password: ", e);
            throw new UserException("Error updating password: " + e.getMessage());
        }
    }

    public UserDTO findPatientByIdentificationNumber(String identificationNumber) {
        try {
            log.info("Searching for patient with identification number: {}", identificationNumber);
            UserEntity userEntity = userRepository.findByIdentificationNumberAndUserType(identificationNumber, UserType.PATIENT)
                    .orElseThrow(() -> new UserException("Patient not found with identification number: " + identificationNumber));

            log.info("Patient found: {}", userEntity);
            return userMapper.toUserDTO(userEntity);

        } catch (UserException e) {
            log.error("Patient not found: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Error searching for patient with identification number: {}", identificationNumber, e);
            throw new UserException("An unexpected error occurred while searching for the patient");
        }
    }

}
