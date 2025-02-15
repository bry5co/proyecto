package com.codeElevate.ServiceBookingSystem.dto;

import lombok.Data;

@Data
public class AuthenticationRequest {

    private String username;
    private String password;

    public AuthenticationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
