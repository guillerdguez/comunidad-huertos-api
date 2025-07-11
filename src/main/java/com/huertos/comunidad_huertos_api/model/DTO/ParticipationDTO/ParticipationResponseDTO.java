package com.huertos.comunidad_huertos_api.model.DTO.ParticipationDTO;

import java.util.UUID;

import com.huertos.comunidad_huertos_api.model.entity.enums.ParticipationStatus;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ParticipationResponseDTO {

	private UUID id;
	private ParticipationStatus status;
	private UUID eventId;
	private UUID userId;

}
