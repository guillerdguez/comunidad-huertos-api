package com.huertos.comunidad_huertos_api.DTO.EventDTO;

import java.time.LocalDateTime;
import java.util.UUID;

public class EventResponseDTO {

	private UUID id;
	private String title;
	private String description;
	private LocalDateTime eventDate;
	private UUID gardenId;
	private UUID createdById;

	// getters & setters
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

	public UUID getCreatedById() {
		return createdById;
	}

	public void setCreatedById(UUID createdById) {
		this.createdById = createdById;
	}
}
