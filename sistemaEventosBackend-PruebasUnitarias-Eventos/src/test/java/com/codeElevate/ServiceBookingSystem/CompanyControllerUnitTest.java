package com.codeElevate.ServiceBookingSystem;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import com.codeElevate.ServiceBookingSystem.controller.admin.CompanyController;
import com.codeElevate.ServiceBookingSystem.dto.event.AdDTO;
import com.codeElevate.ServiceBookingSystem.services.company.CompanyService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import static org.junit.Assert.assertEquals;

// Usar el contexto de Spring para la prueba
@RunWith(MockitoJUnitRunner.class)
public class CompanyControllerUnitTest {

    @Mock
    private CompanyService companyService;

    @InjectMocks
    private CompanyController companyController;

    private AdDTO validAdDTO;
    private AdDTO emptyAdDTO;
    private MultipartFile mockImage;

    @Before
    public void setup() {
        validAdDTO = new AdDTO();
        validAdDTO.setServiceName("The Great Gatsby");
        validAdDTO.setDescription("A classic novel by F. Scott Fitzgerald");
        validAdDTO.setPrice(10.99);

        emptyAdDTO = new AdDTO();

        // Mock image file
        mockImage = new MockMultipartFile("image", "test.jpg", "image/jpeg", new byte[10]);
        validAdDTO.setImg(mockImage);
    }

    @Test
    public void testPostAdWithValidData() throws IOException {
        // Mocking the behavior of companyService.postAd()
        when(companyService.postAd(anyLong(), any(AdDTO.class))).thenReturn(true);

        // Calling the controller method
        assertEquals("Registration should succeed with valid data",
                companyController.postAd(1L, validAdDTO).getStatusCodeValue(), 200);

        // Verifying that companyService.postAd() was called with the correct arguments
        verify(companyService, times(1)).postAd(anyLong(), eq(validAdDTO));
    }

    @Test
    public void testPostAdWithEmptyData() throws IOException {
        // Llamando al método del controlador con datos vacíos
        assertEquals("La registración debería fallar con datos vacíos",
                HttpStatus.BAD_REQUEST.value(), // Cambiado a HttpStatus.BAD_REQUEST
                companyController.postAd(1L, emptyAdDTO).getStatusCodeValue());

        // Verificar que companyService.postAd() no fue llamado
        verify(companyService, never()).postAd(anyLong(), any(AdDTO.class));
    }

    @Test
    public void testUpdateAdWithCompleteData() throws IOException {
        // Datos de ejemplo para la actualización completa
        AdDTO adDTO = new AdDTO();
        adDTO.setServiceName("Nuevo nombre de servicio");
        adDTO.setDescription("Nueva descripción del servicio");
        // Agrega otros campos según tus necesidades

        // Simula que la actualización fue exitosa en el servicio
        when(companyService.updateAd(anyLong(), any(AdDTO.class))).thenReturn(true);

        // Llama al método del controlador para actualizar el anuncio
        ResponseEntity<?> response = companyController.updateAd(1L, adDTO);

        // Verifica que se haya llamado al servicio con los parámetros correctos
        verify(companyService, times(1)).updateAd(1L, adDTO);
        // Verifica que la respuesta sea un código de estado OK (200)
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testUpdateAdWithEmptyData() throws IOException {
        // Datos de ejemplo para la actualización con datos nulos/en blanco
        AdDTO emptyAdDTO = new AdDTO();

        // Simula que la actualización falló en el servicio
        when(companyService.updateAd(anyLong(), any(AdDTO.class))).thenReturn(false);

        // Llama al método del controlador para actualizar el anuncio
        ResponseEntity<?> response = companyController.updateAd(1L, emptyAdDTO);

        // Verifica que se haya llamado al servicio con los parámetros correctos
        verify(companyService, times(1)).updateAd(1L, emptyAdDTO);
        // Verifica que la respuesta sea un código de estado NOT FOUND (404)
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testDeleteAdById() {
        // ID del anuncio a eliminar
        Long adId = 1L;

        // Mock del servicio
        when(companyService.deleteAd(adId)).thenReturn(true);

        // Llamar al método del controlador para eliminar el anuncio
        ResponseEntity<?> response = companyController.deleteAd(adId);

        // Verificar que se haya llamado al servicio con el ID correcto
        verify(companyService, times(1)).deleteAd(adId);
        // Verificar que la respuesta sea un código de estado OK (200)
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
