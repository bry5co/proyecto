package com.codeElevate.ServiceBookingSystem.repository.task;

import com.codeElevate.ServiceBookingSystem.entity.task.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByTaskId(Long taskId);
}
