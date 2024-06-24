package com.codeElevate.ServiceBookingSystem.dto.task;

import com.codeElevate.ServiceBookingSystem.enums.task.TaskStatus;
import lombok.Data;

import java.util.Date;

@Data
public class TaskDTO {
    private Long id;

    private String title;

    private String description;

    private Date dueDate;

    private String priority;

    private Long employeeId;

    private String employeeName;

    private TaskStatus taskStatus;
}
