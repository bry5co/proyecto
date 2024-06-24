package com.codeElevate.ServiceBookingSystem.dto.club;

import lombok.Data;

@Data
public class ClubDTO {
    private Long id;

    private Long idUserAdmin;

    private String name;

    private String description;
}
