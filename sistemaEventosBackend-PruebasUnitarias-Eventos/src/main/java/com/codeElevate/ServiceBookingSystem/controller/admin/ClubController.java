package com.codeElevate.ServiceBookingSystem.controller.admin;

import com.codeElevate.ServiceBookingSystem.dto.club.ClubInscriptionDTO;
import com.codeElevate.ServiceBookingSystem.services.company.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/company")
@RequiredArgsConstructor
public class ClubController {

    private final ClubService clubService;

    @PostMapping("/club")
    public ResponseEntity<?> postClub(@RequestBody ClubInscriptionDTO clubInscriptionDTO) {
        boolean createdClub = clubService.postClubUser(clubInscriptionDTO);
        if (createdClub) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdClub);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/clubs")
    public ResponseEntity<?> getAllClubs() {
        return ResponseEntity.ok(clubService.getClubInscriptions());
    }
}
