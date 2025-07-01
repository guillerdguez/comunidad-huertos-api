package com.huertos.comunidad_huertos_api.DTO.EventDTO;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UpdateEventDTO {

	@NotNull(message = "El ID del evento es obligatorio")
	private UUID id;

	@NotBlank(message = "El título es obligatorio")
	private String title;

	@NotBlank(message = "La descripción es obligatoria")
	private String description;

	@NotNull(message = "La fecha del evento es obligatoria")
	@FutureOrPresent(message = "La fecha del evento no puede ser pasada")
	private LocalDateTime eventDate;

	@NotNull(message = "El ID del huerto es obligatorio")
	private UUID gardenId;

	public UpdateEventDTO() {
	}

	public UpdateEventDTO(UUID id, String title, String description, LocalDateTime eventDate, UUID gardenId) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.eventDate = eventDate;
		this.gardenId = gardenId;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
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
