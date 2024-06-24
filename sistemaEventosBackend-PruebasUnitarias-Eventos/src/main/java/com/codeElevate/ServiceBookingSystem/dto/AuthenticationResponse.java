package com.codeElevate.ServiceBookingSystem.dto;

import lombok.Data;

@Data
public class AuthenticationResponse {
    private Long userId;
    private String role;
    private String token;

    public AuthenticationResponse() {
    }

    public AuthenticationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
