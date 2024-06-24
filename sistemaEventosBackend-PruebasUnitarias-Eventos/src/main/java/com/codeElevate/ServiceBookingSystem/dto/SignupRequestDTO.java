package com.codeElevate.ServiceBookingSystem.dto;

import com.codeElevate.ServiceBookingSystem.enums.UserRole;
import lombok.Data;

@Data
public class SignupRequestDTO {

    private Long id;

    private String email;

    private String password;

    private String name;

    private String lastname;

    private String phone;

    public SignupRequestDTO(String email, String password, String name, String lastname, String phone) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.phone = phone;
    }
}
