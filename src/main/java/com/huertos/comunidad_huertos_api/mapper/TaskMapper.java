package com.huertos.comunidad_huertos_api.mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.huertos.comunidad_huertos_api.model.DTO.TaskDTO.TaskRequestDTO;
import com.huertos.comunidad_huertos_api.model.DTO.TaskDTO.TaskResponseDTO;
import com.huertos.comunidad_huertos_api.model.entity.Plot;
import com.huertos.comunidad_huertos_api.model.entity.Task;
import com.huertos.comunidad_huertos_api.model.entity.User;

public class TaskMapper {
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

	public static TaskResponseDTO toDTO(Task task) {
		TaskResponseDTO dto = new TaskResponseDTO();
		dto.setId(task.getId());
		dto.setDescription(task.getDescription());
		dto.setDueDate(task.getDueDate().format(FORMATTER));
		dto.setStatus(task.getStatus());
		dto.setPlotId(task.getPlot().getId());
		dto.setAssigneeId(task.getAssignee().getId());
		return dto;
	}

	public static Task toModel(TaskRequestDTO dto) {
		Task task = new Task();
		task.setDescription(dto.getDescription());
		LocalDateTime dueDate = LocalDateTime.parse(dto.getDueDate(), FORMATTER);
		task.setDueDate(dueDate);
		task.setStatus(dto.getStatus());
		Plot plot = new Plot();
		plot.setId(dto.getPlotId());
		task.setPlot(plot);
		User assignee = new User();
		assignee.setId(dto.getAssigneeId());
		task.setAssignee(assignee);
		return task;
	}
}
