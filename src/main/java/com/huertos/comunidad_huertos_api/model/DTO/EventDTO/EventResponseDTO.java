package com.huertos.comunidad_huertos_api.model.DTO.EventDTO;

import java.util.UUID;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EventResponseDTO {

	private UUID id;
	private String title;
	private String description;
	private String eventDate;
	private UUID gardenId;
	private UUID createdById;

}
