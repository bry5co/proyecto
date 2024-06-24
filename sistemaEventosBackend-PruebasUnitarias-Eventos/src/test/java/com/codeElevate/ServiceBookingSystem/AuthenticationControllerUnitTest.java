package com.codeElevate.ServiceBookingSystem;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import com.codeElevate.ServiceBookingSystem.controller.AuthenticationController;
import com.codeElevate.ServiceBookingSystem.dto.AuthenticationRequest;
import com.codeElevate.ServiceBookingSystem.dto.AuthenticationResponse;
import com.codeElevate.ServiceBookingSystem.services.jwt.UserDetailsServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.io.IOException;

public class AuthenticationControllerUnitTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserDetailsServiceImpl userDetailsService;

    @InjectMocks
    private AuthenticationController authenticationController;

    @Mock
    private HttpServletResponse httpServletResponse;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSignInWithValidCredentials() throws Exception {
        String email = "example@example.com";
        String password = "password";
        String token = "some_generated_token";
        AuthenticationRequest request = new AuthenticationRequest(email, password);

        UserDetails userDetails = mock(UserDetails.class);
        when(userDetailsService.loadUserByUsername(email)).thenReturn(userDetails);

        ResponseEntity<?> responseEntity = authenticationController.createsAuthenticationToken(request, httpServletResponse);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(token, ((AuthenticationResponse) responseEntity.getBody()).getToken());
    }

    @Test
    public void testSignInWithInvalidCredentials() throws Exception {
        String username = "example";
        String password = "wrong_password";
        AuthenticationRequest request = new AuthenticationRequest(username, password);

        when(userDetailsService.loadUserByUsername(username)).thenThrow(new UsernameNotFoundException("Invalid credentials"));

        ResponseEntity<?> responseEntity = authenticationController.createsAuthenticationToken(request, httpServletResponse);

        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
        assertEquals("Invalid credentials", responseEntity.getBody());
    }

    @Test
    public void testSignInWithBlankCredentials() throws Exception {
        String email = " ";
        String password = " ";
        AuthenticationRequest request = new AuthenticationRequest(email, password);

        ResponseEntity<?> responseEntity = authenticationController.createsAuthenticationToken(request, httpServletResponse);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }
}
