package com.codeElevate.ServiceBookingSystem;

import com.codeElevate.ServiceBookingSystem.controller.admin.ExpenseController;
import com.codeElevate.ServiceBookingSystem.dto.finance.ExpenseDTO;
import com.codeElevate.ServiceBookingSystem.entity.finance.Expense;
import com.codeElevate.ServiceBookingSystem.services.company.ExpenseService;
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

public class ExpenseControllerTest {

    @Mock
    private ExpenseService expenseService;

    @InjectMocks
    private ExpenseController expenseController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPostExpense_Success() {
        // Arrange
        ExpenseDTO expenseDTO = new ExpenseDTO();
        Expense expense = new Expense();
        when(expenseService.postExpense(expenseDTO)).thenReturn(expense);

        // Act
        ResponseEntity<?> response = expenseController.postExpense(expenseDTO);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expense, response.getBody());
        verify(expenseService, times(1)).postExpense(expenseDTO);
    }

    @Test
    void testPostExpense_Failure() {
        // Arrange
        ExpenseDTO expenseDTO = new ExpenseDTO();
        when(expenseService.postExpense(expenseDTO)).thenReturn(null);

        // Act
        ResponseEntity<?> response = expenseController.postExpense(expenseDTO);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(expenseService, times(1)).postExpense(expenseDTO);
    }

    @Test
    void testGetAllExpenses() {
        // Arrange
        List<Expense> expenses = new ArrayList<>();
        when(expenseService.getAllExpenses()).thenReturn(expenses);

        // Act
        ResponseEntity<?> response = expenseController.getAllExpenses();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expenses, response.getBody());
        verify(expenseService, times(1)).getAllExpenses();
    }

    @Test
    void testGetExpenseById_Success() {
        // Arrange
        Long id = 1L;
        Expense expense = new Expense();
        when(expenseService.getExpenseById(id)).thenReturn(expense);

        // Act
        ResponseEntity<?> response = expenseController.getExpenseById(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expense, response.getBody());
        verify(expenseService, times(1)).getExpenseById(id);
    }

    @Test
    void testGetExpenseById_NotFound() {
        // Arrange
        Long id = 1L;
        when(expenseService.getExpenseById(id)).thenThrow(new EntityNotFoundException("Expense is not present with id " + id));

        // Act
        ResponseEntity<?> response = expenseController.getExpenseById(id);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Expense is not present with id " + id, response.getBody());
        verify(expenseService, times(1)).getExpenseById(id);
    }

    @Test
    void testUpdateExpense_Success() {
        // Arrange
        Long id = 1L;
        ExpenseDTO expenseDTO = new ExpenseDTO();
        Expense expense = new Expense();
        when(expenseService.updateExpense(id, expenseDTO)).thenReturn(expense);

        // Act
        ResponseEntity<?> response = expenseController.updateExpense(id, expenseDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expense, response.getBody());
        verify(expenseService, times(1)).updateExpense(id, expenseDTO);
    }

    @Test
    void testUpdateExpense_NotFound() {
        // Arrange
        Long id = 1L;
        ExpenseDTO expenseDTO = new ExpenseDTO();
        when(expenseService.updateExpense(id, expenseDTO)).thenThrow(new EntityNotFoundException("Expense is not present with id " + id));

        // Act
        ResponseEntity<?> response = expenseController.updateExpense(id, expenseDTO);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Expense is not present with id " + id, response.getBody());
        verify(expenseService, times(1)).updateExpense(id, expenseDTO);
    }

    @Test
    void testDeleteExpense_Success() {
        // Arrange
        Long id = 1L;
        doNothing().when(expenseService).deleteExpense(id);

        // Act
        ResponseEntity<?> response = expenseController.deleteExpense(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(expenseService, times(1)).deleteExpense(id);
    }

    @Test
    void testDeleteExpense_NotFound() {
        // Arrange
        Long id = 1L;
        doThrow(new EntityNotFoundException("Expense is not present with id " + id)).when(expenseService).deleteExpense(id);

        // Act
        ResponseEntity<?> response = expenseController.deleteExpense(id);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Expense is not present with id " + id, response.getBody());
        verify(expenseService, times(1)).deleteExpense(id);
    }
}
