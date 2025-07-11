package com.huertos.comunidad_huertos_api.services;

import java.util.List;
import java.util.UUID;

import com.huertos.comunidad_huertos_api.model.DTO.TaskDTO.TaskRequestDTO;
import com.huertos.comunidad_huertos_api.model.DTO.TaskDTO.TaskResponseDTO;

public interface TaskService {

	TaskResponseDTO createTask(TaskRequestDTO task);

	TaskResponseDTO updateTask(UUID id, TaskRequestDTO task);

	List<TaskResponseDTO> findAll();

	TaskResponseDTO findById(UUID id);

	void deleteById(UUID id);
	// extra

	List<TaskResponseDTO> getPendingTasksByUser(UUID userId);

	List<TaskResponseDTO> getOverdue();

}
