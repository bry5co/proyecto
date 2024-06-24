package com.codeElevate.ServiceBookingSystem.entity.task;

import com.codeElevate.ServiceBookingSystem.dto.task.TaskDTO;
import com.codeElevate.ServiceBookingSystem.entity.User;
import com.codeElevate.ServiceBookingSystem.enums.task.TaskStatus;
import jakarta.persistence.Entity;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private Date dueDate;

    private String priority;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    private TaskStatus taskStatus;


    public TaskDTO getTaskDTO() {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(id);
        taskDTO.setTitle(title);
        taskDTO.setDescription(description);
        taskDTO.setDueDate(dueDate);
        taskDTO.setPriority(priority);
        taskDTO.setTaskStatus(taskStatus);
        taskDTO.setEmployeeId(user.getId());
        taskDTO.setEmployeeName(user.getName());
        return taskDTO;
    }
}
