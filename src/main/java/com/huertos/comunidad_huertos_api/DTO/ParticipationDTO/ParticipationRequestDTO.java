package com.huertos.comunidad_huertos_api.DTO.ParticipationDTO;

import java.util.UUID;

import com.huertos.comunidad_huertos_api.model.enums.ParticipationStatus;

import jakarta.validation.constraints.NotNull;

public class ParticipationRequestDTO {

	@NotNull
	private ParticipationStatus status;

	@NotNull
	private UUID eventId;

	@NotNull
	private UUID userId;

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
