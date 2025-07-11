package com.huertos.comunidad_huertos_api.model.DTO.TaskDTO;

import java.util.UUID;

import com.huertos.comunidad_huertos_api.model.entity.enums.TaskStatus;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaskResponseDTO {

	private UUID id;
	private String description;
	private String dueDate;
	private TaskStatus status;
	private UUID plotId;
	private UUID assigneeId;

}
