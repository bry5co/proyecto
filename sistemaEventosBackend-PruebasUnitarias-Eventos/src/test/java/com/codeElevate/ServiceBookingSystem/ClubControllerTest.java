package com.codeElevate.ServiceBookingSystem;

import com.codeElevate.ServiceBookingSystem.controller.admin.ClubController;
import com.codeElevate.ServiceBookingSystem.dto.club.ClubInscriptionDTO;
import com.codeElevate.ServiceBookingSystem.services.company.ClubService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ClubControllerTest {

    @Mock
    private ClubService clubService;

    @InjectMocks
    private ClubController clubController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPostClub_Success() {
        // Arrange
        ClubInscriptionDTO clubInscriptionDTO = new ClubInscriptionDTO();
        when(clubService.postClubUser(clubInscriptionDTO)).thenReturn(true);

        // Act
        ResponseEntity<?> response = clubController.postClub(clubInscriptionDTO);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(true, response.getBody());
        verify(clubService, times(1)).postClubUser(clubInscriptionDTO);
    }

    @Test
    void testPostClub_NotFound() {
        // Arrange
        ClubInscriptionDTO clubInscriptionDTO = new ClubInscriptionDTO();
        when(clubService.postClubUser(clubInscriptionDTO)).thenReturn(false);

        // Act
        ResponseEntity<?> response = clubController.postClub(clubInscriptionDTO);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(null, response.getBody());
        verify(clubService, times(1)).postClubUser(clubInscriptionDTO);
    }
}
