package com.codeElevate.ServiceBookingSystem;

import com.codeElevate.ServiceBookingSystem.controller.admin.TaskCompanyController;
import com.codeElevate.ServiceBookingSystem.dto.task.TaskDTO;
import com.codeElevate.ServiceBookingSystem.entity.task.Task;
import com.codeElevate.ServiceBookingSystem.enums.task.TaskStatus;
import com.codeElevate.ServiceBookingSystem.services.company.TaskService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TaskControllerCompanyUnitTest {

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskCompanyController taskController;

    private TaskDTO validTaskDTO;
    private TaskDTO emptyTaskDTO;
    private Task task;

    @Before
    public void setup() {
        validTaskDTO = new TaskDTO();
        validTaskDTO.setId(1L);
        validTaskDTO.setTitle("Valid Task Title");
        validTaskDTO.setDescription("Valid Task Description");
        validTaskDTO.setDueDate(new Date());
        validTaskDTO.setPriority("High");
        validTaskDTO.setEmployeeId(1L);
        validTaskDTO.setEmployeeName("Employee Name");
        validTaskDTO.setTaskStatus(TaskStatus.PENDING);

        emptyTaskDTO = new TaskDTO();

        task = new Task();
        task.setId(1L);
        task.setTitle("Valid Task Title");
        task.setDescription("Valid Task Description");
        task.setDueDate(new Date());
        task.setPriority("High");
        task.setTaskStatus(TaskStatus.PENDING);
    }

    @Test
    public void testPostTaskWithValidData() {
        when(taskService.postTask(any(TaskDTO.class))).thenReturn(task);

        ResponseEntity<?> response = taskController.postTask(validTaskDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(task, response.getBody());
        verify(taskService, times(1)).postTask(any(TaskDTO.class));
    }

    @Test
    public void testPostTaskWithEmptyData() {
        when(taskService.postTask(any(TaskDTO.class))).thenReturn(null);

        ResponseEntity<?> response = taskController.postTask(emptyTaskDTO);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(taskService, times(1)).postTask(any(TaskDTO.class));
    }

    @Test
    public void testUpdateTaskWithValidData() {
        when(taskService.updateTask(anyLong(), any(TaskDTO.class))).thenReturn(task);

        ResponseEntity<Task> response = taskController.updateTask(1L, validTaskDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(task, response.getBody());
        verify(taskService, times(1)).updateTask(anyLong(), any(TaskDTO.class));
    }

    @Test
    public void testUpdateTaskWithEmptyData() {
        when(taskService.updateTask(anyLong(), any(TaskDTO.class))).thenReturn(null);

        ResponseEntity<Task> response = taskController.updateTask(1L, emptyTaskDTO);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(taskService, times(1)).updateTask(anyLong(), any(TaskDTO.class));
    }

    @Test
    public void testDeleteTaskById() {
        // ID del task a eliminar
        Long taskId = 1L;

        // Mock del servicio para eliminar el task
        doNothing().when(taskService).deleteTask(taskId);

        // Llamar al método del controlador para eliminar el task
        ResponseEntity<Void> response = taskController.deleteTask(taskId);

        // Verificar que se haya llamado al servicio con el ID correcto
        verify(taskService, times(1)).deleteTask(taskId);
        // Verificar que la respuesta sea un código de estado OK (200)
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
