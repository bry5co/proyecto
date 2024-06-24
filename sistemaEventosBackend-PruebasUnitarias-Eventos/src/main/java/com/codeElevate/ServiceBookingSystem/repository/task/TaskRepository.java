package com.codeElevate.ServiceBookingSystem.repository.task;

import com.codeElevate.ServiceBookingSystem.entity.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUserId(Long id);

    List<Task> findAllByTitleContaining(String title);
}
