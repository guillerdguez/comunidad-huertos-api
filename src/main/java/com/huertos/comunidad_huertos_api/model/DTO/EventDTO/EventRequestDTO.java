package com.huertos.comunidad_huertos_api.model.DTO.EventDTO;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EventRequestDTO {

	@NotBlank
	@Size(max = 50)
	private String title;

	@NotBlank
	@Size(max = 50)
	private String description;

	@NotNull
	private String eventDate;

	@NotNull
	private UUID gardenId;

	@NotNull
	private UUID createdById;

}
