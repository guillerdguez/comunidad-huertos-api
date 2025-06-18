package com.huertos.comunidad_huertos_api.model.DTO.EventDTO;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateEventDTO {

	@NotBlank(message = "El título es obligatorio")
	private String title;

	@NotBlank(message = "La descripción es obligatoria")
	private String description;

	@NotNull(message = "La fecha del evento es obligatoria")
	@Future(message = "La fecha del evento debe ser futura")
	private LocalDateTime eventDate;

	@NotNull(message = "El ID del huerto es obligatorio")
	private UUID gardenId;

	public CreateEventDTO() {

	}

	public CreateEventDTO(String title, String description, LocalDateTime eventDate, UUID gardenId) {
		this.title = title;
		this.description = description;
		this.eventDate = eventDate;
		this.gardenId = gardenId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getEventDate() {
		return eventDate;
	}

	public void setEventDate(LocalDateTime eventDate) {
		this.eventDate = eventDate;
	}

	public UUID getGardenId() {
		return gardenId;
	}

	public void setGardenId(UUID gardenId) {
		this.gardenId = gardenId;
	}
}
