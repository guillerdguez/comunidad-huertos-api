package com.huertos.comunidad_huertos_api.servicesImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huertos.comunidad_huertos_api.exception.PlotNotFoundException;
import com.huertos.comunidad_huertos_api.exception.TaskNotFoundException;
import com.huertos.comunidad_huertos_api.mapper.TaskMapper;
import com.huertos.comunidad_huertos_api.model.DTO.TaskDTO.TaskRequestDTO;
import com.huertos.comunidad_huertos_api.model.DTO.TaskDTO.TaskResponseDTO;
import com.huertos.comunidad_huertos_api.model.entity.Plot;
import com.huertos.comunidad_huertos_api.model.entity.Task;
import com.huertos.comunidad_huertos_api.model.entity.User;
import com.huertos.comunidad_huertos_api.model.entity.enums.TaskStatus;
import com.huertos.comunidad_huertos_api.repository.PlotRepository;
import com.huertos.comunidad_huertos_api.repository.TaskRepository;
import com.huertos.comunidad_huertos_api.repository.UserRepository;
import com.huertos.comunidad_huertos_api.services.TaskService;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

	private static final Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);
	private final TaskRepository repository;
	private final PlotRepository plotRepository;
	private final UserRepository userRepository;

	public TaskServiceImpl(TaskRepository repository, PlotRepository plotRepository, UserRepository userRepository) {
		this.repository = repository;
		this.plotRepository = plotRepository;
		this.userRepository = userRepository;
	}

	@Override
	public TaskResponseDTO createTask(TaskRequestDTO task) {

		log.debug("Guardando task: {}", task);

		Task newTask = repository.save(TaskMapper.toModel(task));
		return TaskMapper.toDTO(newTask);
	}

	@Override
	public TaskResponseDTO updateTask(UUID id, TaskRequestDTO taskRequest) {
		Task task = repository.findById(id)
				.orElseThrow(() -> new TaskNotFoundException("Task not found with ID: " + id));

		task.setDescription(taskRequest.getDescription());
		task.setStatus(taskRequest.getStatus());

		task.setDueDate(
				LocalDateTime.parse(taskRequest.getDueDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));

		Plot plot = plotRepository.findById(taskRequest.getPlotId())
				.orElseThrow(() -> new PlotNotFoundException("Plot not found with ID: " + taskRequest.getPlotId()));
		task.setPlot(plot);

		User user = userRepository.findById(taskRequest.getAssigneeId())
				.orElseThrow(() -> new PlotNotFoundException("User not found with ID: " + taskRequest.getAssigneeId()));
		task.setAssignee(user);
		Task updatedTask = repository.save(task);

		return TaskMapper.toDTO(updatedTask);
	}

	@Override
	@Transactional(readOnly = true)
	public List<TaskResponseDTO> findAll() {
		log.debug("Recuperando todos los tasks");

		List<Task> tasks = repository.findAll();

		return tasks.stream().map(TaskMapper::toDTO).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public TaskResponseDTO findById(UUID id) {
		Task task = repository.findById(id)
				.orElseThrow(() -> new TaskNotFoundException("Task not found with ID: " + id));
		return TaskMapper.toDTO(task);
	}

	@Override
	public void deleteById(UUID id) {
		log.debug("Eliminando task por ID: {}", id);
		repository.deleteById(id);
	}

	@Override
	public List<TaskResponseDTO> getPendingTasksByUser(UUID userId) {
		List<Task> tasks = repository.findByAssignee_IdAndStatus(userId, TaskStatus.PENDING);

		return tasks.stream().map(TaskMapper::toDTO).collect(Collectors.toList());
	}

	@Override
	public List<TaskResponseDTO> getOverdue() {
		List<Task> tasks = repository.findByDueDateBeforeAndStatus(LocalDateTime.now(), TaskStatus.PENDING);
		return tasks.stream().map(TaskMapper::toDTO).collect(Collectors.toList());
	}

}
