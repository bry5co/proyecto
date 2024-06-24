package com.codeElevate.ServiceBookingSystem.services.company;

import com.codeElevate.ServiceBookingSystem.dto.club.ClubInscriptionDTO;
import com.codeElevate.ServiceBookingSystem.entity.User;

import java.util.List;

public interface ClubService {
    boolean postClubUser(ClubInscriptionDTO clubInscriptionDTO);

    List<ClubInscriptionDTO> getClubInscriptions();
}
