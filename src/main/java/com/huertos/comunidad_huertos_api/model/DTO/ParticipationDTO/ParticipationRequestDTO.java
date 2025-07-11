package com.huertos.comunidad_huertos_api.model.DTO.ParticipationDTO;

import java.util.UUID;

import com.huertos.comunidad_huertos_api.model.entity.enums.ParticipationStatus;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ParticipationRequestDTO {

	@NotNull
	private ParticipationStatus status;

	@NotNull
	private UUID eventId;

	@NotNull
	private UUID userId;

}
