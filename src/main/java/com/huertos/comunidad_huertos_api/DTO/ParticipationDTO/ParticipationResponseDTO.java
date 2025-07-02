package com.huertos.comunidad_huertos_api.DTO.ParticipationDTO;

import java.util.UUID;

import com.huertos.comunidad_huertos_api.model.enums.ParticipationStatus;

public class ParticipationResponseDTO {

	private UUID id;
	private ParticipationStatus status;
	private UUID eventId;
	private UUID userId;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public ParticipationStatus getStatus() {
		return status;
	}

	public void setStatus(ParticipationStatus status) {
		this.status = status;
	}

	public UUID getEventId() {
		return eventId;
	}

	public void setEventId(UUID eventId) {
		this.eventId = eventId;
	}

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}
}
