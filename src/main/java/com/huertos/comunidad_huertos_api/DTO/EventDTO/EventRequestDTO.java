package com.huertos.comunidad_huertos_api.DTO.EventDTO;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

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

	// getters & setters
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

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public UUID getGardenId() {
		return gardenId;
	}

	public void setGardenId(UUID gardenId) {
		this.gardenId = gardenId;
	}

	public UUID getCreatedById() {
		return createdById;
	}

	public void setCreatedById(UUID createdById) {
		this.createdById = createdById;
	}
}
