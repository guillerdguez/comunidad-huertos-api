package com.huertos.comunidad_huertos_api.servicesImpl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.huertos.comunidad_huertos_api.model.Task;
import com.huertos.comunidad_huertos_api.services.TaskService;

public class TaskServiceImpl implements TaskService {

	@Override
	public Task save(Task task) {

		return null;
	}

	@Override
	public List<Task> findAll() {

		return null;
	}

	@Override
	public Optional<Task> findById(UUID id) {

		return Optional.empty();
	}

	@Override
	public void deleteById(UUID id) {

	}

}
