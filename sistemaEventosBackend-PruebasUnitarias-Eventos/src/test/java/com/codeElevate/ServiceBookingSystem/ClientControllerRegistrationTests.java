package com.codeElevate.ServiceBookingSystem;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.codeElevate.ServiceBookingSystem.dto.SignupRequestDTO;
import com.codeElevate.ServiceBookingSystem.dto.UserDto;
import com.codeElevate.ServiceBookingSystem.enums.UserRole;
import com.codeElevate.ServiceBookingSystem.services.authentication.AuthService;
import com.codeElevate.ServiceBookingSystem.controller.user.ClientController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class ClientControllerRegistrationTests {

    @Mock
    private AuthService authService;

    @InjectMocks
    private ClientController clientController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRegisterClientWithCompleteData() {
        // Datos completos de registro
        SignupRequestDTO requestDTO = new SignupRequestDTO("john@example.com", "password", "John", "Doe", "123456789");
        UserDto createdUser = new UserDto();
        createdUser.setEmail("john@example.com");
        createdUser.setPassword("password");
        createdUser.setName("John");
        createdUser.setLastname("Doe");
        createdUser.setPhone("123456789");
        createdUser.setRole(UserRole.CLIENT);

        when(authService.signupClient(any())).thenReturn(createdUser);

        ResponseEntity<?> responseEntity = clientController.signup(requestDTO);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(createdUser, responseEntity.getBody());
    }

    @Test
    public void testRegisterUserWithNullValues() {
        // Datos nulos o en blanco
        SignupRequestDTO requestDTO = new SignupRequestDTO(null, null, null, null, null);

        // Simula la respuesta del servicio de autenticación
        when(authService.signupClient(requestDTO)).thenReturn(null);

        // Realiza la solicitud al controlador
        ResponseEntity<?> responseEntity = clientController.signup(requestDTO);

        // Verifica que el controlador responda con un estado 400 BAD_REQUEST
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }
}