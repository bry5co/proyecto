package com.codeElevate.ServiceBookingSystem.services.company;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.codeElevate.ServiceBookingSystem.dto.task.CommentDTO;
import com.codeElevate.ServiceBookingSystem.dto.task.TaskDTO;
import com.codeElevate.ServiceBookingSystem.dto.UserDto;
import com.codeElevate.ServiceBookingSystem.entity.task.Comment;
import com.codeElevate.ServiceBookingSystem.entity.task.Task;
import com.codeElevate.ServiceBookingSystem.entity.User;
import com.codeElevate.ServiceBookingSystem.enums.task.TaskStatus;
import com.codeElevate.ServiceBookingSystem.enums.UserRole;
import com.codeElevate.ServiceBookingSystem.repository.task.CommentRepository;
import com.codeElevate.ServiceBookingSystem.repository.task.TaskRepository;
import com.codeElevate.ServiceBookingSystem.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("TaskServiceCompany")
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService{
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    @Override
    public Task postTask(TaskDTO taskDTO) {
        Optional<User> optionalUser = userRepository.findById(taskDTO.getEmployeeId());
        if (optionalUser.isPresent()) {
            Task task = new Task();
            task.setTitle(taskDTO.getTitle());
            task.setDescription(taskDTO.getDescription());
            task.setPriority(taskDTO.getPriority());
            task.setDueDate(taskDTO.getDueDate());
            task.setTaskStatus(TaskStatus.PENDING);
            task.setUser(optionalUser.get());
            return taskRepository.save(task);
        } else {
            return null;
        }
    }

    @Override
    public List<TaskDTO> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Task::getDueDate).reversed())
                .map(Task::getTaskDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TaskDTO getTaskById(Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        return optionalTask.map(Task::getTaskDTO).orElse(null);
    }

    @Override
    public Task updateTask(Long id, TaskDTO taskDTO) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task existingTask = optionalTask.get();
            existingTask.setTitle(taskDTO.getTitle());
            existingTask.setDescription(taskDTO.getDescription());
            existingTask.setPriority(taskDTO.getPriority());
            existingTask.setDueDate(taskDTO.getDueDate());
            TaskStatus newStatus = mapStringToTaskStatus(String.valueOf(taskDTO.getTaskStatus()));
            existingTask.setTaskStatus(newStatus);
            return taskRepository.save(existingTask);
        }
        return null;
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .filter(user -> user.getRole() == UserRole.CLIENT)
                .map(User::getDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> searchTasks(String title) {
        return taskRepository.findAllByTitleContaining(title)
                .stream()
                .sorted(Comparator.comparing(Task::getDueDate).reversed())
                .map(Task::getTaskDTO)
                .collect(Collectors.toList());
    }

    private TaskStatus mapStringToTaskStatus(String statusString) {
        return switch (statusString) {
            case "PENDING" -> TaskStatus.PENDING;
            case "INPROGRESS" -> TaskStatus.INPROGRESS;
            case "COMPLETED" -> TaskStatus.COMPLETED;
            case "DEFERRED" -> TaskStatus.DEFERRED;
            default -> TaskStatus.CANCELLED;
        };
    }


    public Comment createComment(Long taskId, Long postedBy, String content){
        Optional<Task> optionalTask = taskRepository.findById(taskId);
        Optional<User> optionalUser = userRepository.findById(taskId);
        if(optionalTask.isPresent() && optionalUser.isPresent()){
            Comment comment = new Comment();

            comment.setTask(optionalTask.get());
            comment.setContent(content);
            comment.setPostedBy(optionalUser.get());
            comment.setCreatedAt(new Date());

            return commentRepository.save(comment);
        }
        throw new EntityNotFoundException("Task not found");
    }

    public List<CommentDTO> getCommentsByTaskId(Long taskId){
        return commentRepository.findByTaskId(taskId).stream().map(Comment::getDto).collect(Collectors.toList());
    }
}
