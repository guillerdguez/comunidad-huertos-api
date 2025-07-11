package com.huertos.comunidad_huertos_api.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.huertos.comunidad_huertos_api.model.entity.Task;
import com.huertos.comunidad_huertos_api.model.entity.enums.TaskStatus;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {
	List<Task> findByAssignee_IdAndStatus(UUID userId, TaskStatus status);

	List<Task> findByDueDateBeforeAndStatus(LocalDateTime dateTime, TaskStatus status);
}
