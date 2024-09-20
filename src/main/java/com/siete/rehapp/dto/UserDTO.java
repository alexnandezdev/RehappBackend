package com.siete.rehapp.dto;

import com.siete.rehapp.entity.PhysiotherapistEntity;
import com.siete.rehapp.enums.UserType;
import lombok.Data;

@Data
public class UserDTO {

    private Long userId;
    private String userName;
    private String password;
    private String identificationNumber;
    private int age;
    private String sex;
    private String email;
    private String phoneNumber;
    private String city;
    private UserType userType;
    private String professionalCardNumber;

}
