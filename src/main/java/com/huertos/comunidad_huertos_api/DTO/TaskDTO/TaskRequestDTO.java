package com.huertos.comunidad_huertos_api.DTO.TaskDTO;

import java.time.LocalDateTime;
import java.util.UUID;

import com.huertos.comunidad_huertos_api.model.enums.TaskStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TaskRequestDTO {

	@NotBlank
	private String description;

	@NotNull
	private LocalDateTime dueDate;

	private TaskStatus status;

	@NotNull
	private UUID plotId;

	@NotNull
	private UUID assigneeId;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public UUID getPlotId() {
		return plotId;
	}

	public void setPlotId(UUID plotId) {
		this.plotId = plotId;
	}

	public UUID getAssigneeId() {
		return assigneeId;
	}

	public void setAssigneeId(UUID assigneeId) {
		this.assigneeId = assigneeId;
	}
}
