package com.codeElevate.ServiceBookingSystem.dto.club;

import lombok.Data;

import java.util.Date;

@Data
public class ClubInscriptionDTO {
    private Long id;

    private Long idUser;

    private Long idClub;

    private Date dateInscription;
}
