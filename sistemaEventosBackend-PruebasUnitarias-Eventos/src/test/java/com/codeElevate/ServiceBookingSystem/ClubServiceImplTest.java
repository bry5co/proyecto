package com.codeElevate.ServiceBookingSystem;

import com.codeElevate.ServiceBookingSystem.dto.club.ClubInscriptionDTO;
import com.codeElevate.ServiceBookingSystem.entity.User;
import com.codeElevate.ServiceBookingSystem.entity.club.Club;
import com.codeElevate.ServiceBookingSystem.entity.club.ClubInscription;
import com.codeElevate.ServiceBookingSystem.repository.UserRepository;
import com.codeElevate.ServiceBookingSystem.repository.club.ClubInscriptionRepository;
import com.codeElevate.ServiceBookingSystem.repository.club.ClubRepository;
import com.codeElevate.ServiceBookingSystem.services.company.ClubServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

public class ClubServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ClubRepository clubRepository;

    @Mock
    private ClubInscriptionRepository clubInscriptionRepository;

    @InjectMocks
    private ClubServiceImpl clubServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPostClubUser_Success() {
        // Arrange
        ClubInscriptionDTO clubInscriptionDTO = new ClubInscriptionDTO();
        clubInscriptionDTO.setIdUser(1L);
        clubInscriptionDTO.setIdClub(1L);
        clubInscriptionDTO.setDateInscription(new java.util.Date());

        User user = new User();
        user.setId(1L);

        Club club = new Club();
        club.setId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(clubRepository.findById(1L)).thenReturn(Optional.of(club));

        // Act
        boolean result = clubServiceImpl.postClubUser(clubInscriptionDTO);

        // Assert
        assertTrue(result);
        verify(userRepository, times(1)).findById(1L);
        verify(clubRepository, times(1)).findById(1L);
        verify(clubInscriptionRepository, times(1)).save(any(ClubInscription.class));
    }

    @Test
    void testPostClubUser_UserNotFound() {
        // Arrange
        ClubInscriptionDTO clubInscriptionDTO = new ClubInscriptionDTO();
        clubInscriptionDTO.setIdUser(1L);
        clubInscriptionDTO.setIdClub(1L);
        clubInscriptionDTO.setDateInscription(new java.util.Date());

        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        boolean result = clubServiceImpl.postClubUser(clubInscriptionDTO);

        // Assert
        assertFalse(result);
        verify(userRepository, times(1)).findById(1L);
        verify(clubRepository, never()).findById(anyLong());
        verify(clubInscriptionRepository, never()).save(any(ClubInscription.class));
    }

    @Test
    void testPostClubUser_ClubNotFound() {
        // Arrange
        ClubInscriptionDTO clubInscriptionDTO = new ClubInscriptionDTO();
        clubInscriptionDTO.setIdUser(1L);
        clubInscriptionDTO.setIdClub(1L);
        clubInscriptionDTO.setDateInscription(new java.util.Date());

        User user = new User();
        user.setId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(clubRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        boolean result = clubServiceImpl.postClubUser(clubInscriptionDTO);

        // Assert
        assertFalse(result);
        verify(userRepository, times(1)).findById(1L);
        verify(clubRepository, times(1)).findById(1L);
        verify(clubInscriptionRepository, never()).save(any(ClubInscription.class));
    }
}
