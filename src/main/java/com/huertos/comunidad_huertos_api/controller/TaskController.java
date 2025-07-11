package com.huertos.comunidad_huertos_api.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huertos.comunidad_huertos_api.model.DTO.TaskDTO.TaskRequestDTO;
import com.huertos.comunidad_huertos_api.model.DTO.TaskDTO.TaskResponseDTO;
import com.huertos.comunidad_huertos_api.services.TaskService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/tasks")
public class TaskController {

	private final TaskService service;

	public TaskController(TaskService service) {
		this.service = service;
	}

	@PostMapping
	@Operation(summary = "Create a new Task")
	public ResponseEntity<TaskResponseDTO> create(@RequestBody TaskRequestDTO task) {

		TaskResponseDTO taskResponseDTO = service.createTask(task);

		return ResponseEntity.ok().body(taskResponseDTO);
	}

	@GetMapping
	@Operation(summary = "Get all Tasks")
	public ResponseEntity<List<TaskResponseDTO>> getAll() {
		List<TaskResponseDTO> tasks = service.findAll();

		return ResponseEntity.ok().body(tasks);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Get Task by ID")
	public ResponseEntity<TaskResponseDTO> getTask(@PathVariable UUID id) {
		return ResponseEntity.ok().body(service.findById(id));
	}

	@PutMapping("/{id}")
	@Operation(summary = "Update a new Task")
	public ResponseEntity<TaskResponseDTO> update(@PathVariable UUID id,
			@Valid @RequestBody TaskRequestDTO taskRequest) {

		TaskResponseDTO taskResponseDTO = service.updateTask(id, taskRequest);
		return ResponseEntity.ok().body(taskResponseDTO);

	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete a Task")
	public ResponseEntity<Void> delete(@PathVariable UUID id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();

	}

	// extras
	@GetMapping("/user/{userId}/pending")
	@Operation(summary = "Get Pending tasks by user")
	public ResponseEntity<List<TaskResponseDTO>> getPendingTasksByUser(@PathVariable UUID userId) {
		List<TaskResponseDTO> tasks = service.getPendingTasksByUser(userId);
		return ResponseEntity.ok(tasks);
	}

	@GetMapping("/tasks/overdue")
	@Operation(summary = "Get Pending and OverDue")
	public ResponseEntity<List<TaskResponseDTO>> getOverDue() {
		List<TaskResponseDTO> tasks = service.getOverdue();
		return ResponseEntity.ok(tasks);

	}

}
