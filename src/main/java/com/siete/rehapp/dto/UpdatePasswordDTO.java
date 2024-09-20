package com.siete.rehapp.dto;

import lombok.Data;

@Data
public class UpdatePasswordDTO {

    private String email;
    private String newPassword;

}
