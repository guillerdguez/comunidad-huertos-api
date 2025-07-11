package com.huertos.comunidad_huertos_api.model.DTO.TaskDTO;

import java.util.UUID;

import com.huertos.comunidad_huertos_api.model.entity.enums.TaskStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaskRequestDTO {

	@NotBlank
	private String description;

	@NotNull
	private String dueDate;

	private TaskStatus status;

	@NotNull
	private UUID plotId;

	@NotNull
	private UUID assigneeId;

}
