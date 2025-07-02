package com.huertos.comunidad_huertos_api.mapper;

import com.huertos.comunidad_huertos_api.DTO.TaskDTO.TaskRequestDTO;
import com.huertos.comunidad_huertos_api.DTO.TaskDTO.TaskResponseDTO;
import com.huertos.comunidad_huertos_api.model.Plot;
import com.huertos.comunidad_huertos_api.model.Task;
import com.huertos.comunidad_huertos_api.model.User;

public class TaskMapper {

	public static Task toEntity(TaskRequestDTO dto) {
		Task task = new Task();
		task.setDescription(dto.getDescription());
		task.setDueDate(dto.getDueDate());
		task.setStatus(dto.getStatus());
		Plot plot = new Plot();
		plot.setId(dto.getPlotId());
		task.setPlot(plot);
		User assignee = new User();
		assignee.setId(dto.getAssigneeId());
		task.setAssignee(assignee);
		return task;
	}

	public static TaskResponseDTO toDto(Task task) {
		TaskResponseDTO dto = new TaskResponseDTO();
		dto.setId(task.getId());
		dto.setDescription(task.getDescription());
		dto.setDueDate(task.getDueDate());
		dto.setStatus(task.getStatus());
		dto.setPlotId(task.getPlot().getId());
		dto.setAssigneeId(task.getAssignee().getId());
		return dto;
	}
}
