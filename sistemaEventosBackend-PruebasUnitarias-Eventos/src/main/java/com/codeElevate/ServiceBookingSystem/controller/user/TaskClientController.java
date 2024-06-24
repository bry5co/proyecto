package com.codeElevate.ServiceBookingSystem.controller.user;
import com.codeElevate.ServiceBookingSystem.dto.task.TaskDTO;
import com.codeElevate.ServiceBookingSystem.entity.task.Task;
import com.codeElevate.ServiceBookingSystem.services.client.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/client")
@RequiredArgsConstructor
public class TaskClientController {
    @Qualifier("TaskServiceClient")
    private final TaskService taskService;

    @GetMapping("/tasks/{id}")
    public ResponseEntity<?> getTasksByUserId(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTasksByUserId(id));
    }

    @GetMapping("/task/{taskId}/{status}")
    public ResponseEntity<Task> updateTask(@PathVariable Long taskId, @PathVariable String status) {
        Task updatedTask = taskService.updateTask(taskId, status);
        if (updatedTask != null) {
            return ResponseEntity.status(HttpStatus.OK).body(updatedTask);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/comments/create")
    public ResponseEntity<?> createComment(@RequestParam Long taskId, @RequestParam Long postedBy, @RequestBody String content){
        try {
            return ResponseEntity.ok(taskService.createComment(taskId, postedBy, content));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        }
    }

    @GetMapping("/task/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @GetMapping("/comments/{taskId}")
    public ResponseEntity<?> getCommentsByTaskId(@PathVariable Long taskId){
        try {
            return ResponseEntity.ok(taskService.getCommentsByTaskId(taskId));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something Went Wrong.");
        }
    }
}
