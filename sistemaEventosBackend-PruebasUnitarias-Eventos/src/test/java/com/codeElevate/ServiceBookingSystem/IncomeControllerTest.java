package com.codeElevate.ServiceBookingSystem.controller.admin;

import com.codeElevate.ServiceBookingSystem.dto.finance.IncomeDTO;
import com.codeElevate.ServiceBookingSystem.entity.finance.Income;
import com.codeElevate.ServiceBookingSystem.services.company.IncomeService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class IncomeControllerTest {

    @Mock
    private IncomeService incomeService;

    @InjectMocks
    private IncomeController incomeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPostIncome_Success() {
        // Arrange
        IncomeDTO incomeDTO = new IncomeDTO();
        Income income = new Income();
        when(incomeService.postIncome(incomeDTO)).thenReturn(income);

        // Act
        ResponseEntity<?> response = incomeController.postIncome(incomeDTO);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(income, response.getBody());
        verify(incomeService, times(1)).postIncome(incomeDTO);
    }

    @Test
    void testPostIncome_Failure() {
        // Arrange
        IncomeDTO incomeDTO = new IncomeDTO();
        when(incomeService.postIncome(incomeDTO)).thenReturn(null);

        // Act
        ResponseEntity<?> response = incomeController.postIncome(incomeDTO);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(incomeService, times(1)).postIncome(incomeDTO);
    }

    @Test
    void testGetAllIncomes() {
        // Arrange
        List<IncomeDTO> incomes = new ArrayList<>();
        when(incomeService.getAllIncomes()).thenReturn(incomes);

        // Act
        ResponseEntity<?> response = incomeController.getAllIncomes();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(incomes, response.getBody());
        verify(incomeService, times(1)).getAllIncomes();
    }

    @Test
    void testGetIncomeById_Success() {
        // Arrange
        Long id = 1L;
        IncomeDTO incomeDTO = new IncomeDTO();
        when(incomeService.getIncomeById(id)).thenReturn(incomeDTO);

        // Act
        ResponseEntity<?> response = incomeController.getIncomeById(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(incomeDTO, response.getBody());
        verify(incomeService, times(1)).getIncomeById(id);
    }

    @Test
    void testGetIncomeById_NotFound() {
        // Arrange
        Long id = 1L;
        when(incomeService.getIncomeById(id)).thenThrow(new EntityNotFoundException("Income is not present with id " + id));

        // Act
        ResponseEntity<?> response = incomeController.getIncomeById(id);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Income is not present with id " + id, response.getBody());
        verify(incomeService, times(1)).getIncomeById(id);
    }

    @Test
    void testUpdateIncome_Success() {
        // Arrange
        Long id = 1L;
        IncomeDTO incomeDTO = new IncomeDTO();
        Income income = new Income();
        when(incomeService.updateIncome(id, incomeDTO)).thenReturn(income);

        // Act
        ResponseEntity<?> response = incomeController.updateIncome(id, incomeDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(income, response.getBody());
        verify(incomeService, times(1)).updateIncome(id, incomeDTO);
    }

    @Test
    void testUpdateIncome_NotFound() {
        // Arrange
        Long id = 1L;
        IncomeDTO incomeDTO = new IncomeDTO();
        when(incomeService.updateIncome(id, incomeDTO)).thenThrow(new EntityNotFoundException("Income is not present with id " + id));

        // Act
        ResponseEntity<?> response = incomeController.updateIncome(id, incomeDTO);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Income is not present with id " + id, response.getBody());
        verify(incomeService, times(1)).updateIncome(id, incomeDTO);
    }

    @Test
    void testDeleteIncome_Success() {
        // Arrange
        Long id = 1L;
        doNothing().when(incomeService).deleteIncome(id);

        // Act
        ResponseEntity<?> response = incomeController.deleteIncome(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(incomeService, times(1)).deleteIncome(id);
    }

    @Test
    void testDeleteIncome_NotFound() {
        // Arrange
        Long id = 1L;
        doThrow(new EntityNotFoundException("Income is not present with id " + id)).when(incomeService).deleteIncome(id);

        // Act
        ResponseEntity<?> response = incomeController.deleteIncome(id);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Income is not present with id " + id, response.getBody());
        verify(incomeService, times(1)).deleteIncome(id);
    }
}
