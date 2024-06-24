package com.codeElevate.ServiceBookingSystem.services.client;

import com.codeElevate.ServiceBookingSystem.dto.task.CommentDTO;
import com.codeElevate.ServiceBookingSystem.dto.task.TaskDTO;
import com.codeElevate.ServiceBookingSystem.entity.task.Comment;
import com.codeElevate.ServiceBookingSystem.entity.task.Task;

import java.util.List;

public interface TaskService {
    List<TaskDTO> getTasksByUserId(Long id);

    Task updateTask(Long taskId, String status);

    TaskDTO getTaskById(Long id);

    Comment createComment(Long taskId, Long postedBy, String content);

    List<CommentDTO> getCommentsByTaskId(Long taskId);
}
