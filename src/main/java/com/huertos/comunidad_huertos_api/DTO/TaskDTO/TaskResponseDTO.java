package com.huertos.comunidad_huertos_api.DTO.TaskDTO;
 
import java.util.UUID;

import com.huertos.comunidad_huertos_api.model.enums.TaskStatus;

public class TaskResponseDTO {

	private UUID id;
	private String description;
	private String dueDate;
	private TaskStatus status;
	private UUID plotId;
	private UUID assigneeId;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
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
