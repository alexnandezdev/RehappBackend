package com.siete.rehapp.dto;

import com.siete.rehapp.enums.UserType;
import lombok.Data;

@Data
public class PhysioUserRegisterDTO {

    private String email;
    private String userName;
    private String password;
    private UserType userType;
    private String identificationNumber;
    private String professionalCardNumber;
    private String phoneNumber;

}
