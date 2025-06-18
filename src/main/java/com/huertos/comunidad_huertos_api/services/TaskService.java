package com.huertos.comunidad_huertos_api.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.huertos.comunidad_huertos_api.model.Task;

public interface TaskService {
	Task save(Task task);

	List<Task> findAll();

	Optional<Task> findById(UUID id);

	void deleteById(UUID id);
}
