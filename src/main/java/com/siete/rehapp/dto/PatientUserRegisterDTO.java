package com.siete.rehapp.dto;

import com.siete.rehapp.enums.UserType;
import lombok.Data;

@Data
public class PatientUserRegisterDTO {

    private String email;
    private String userName;
    private String password;
    private UserType userType;

}
