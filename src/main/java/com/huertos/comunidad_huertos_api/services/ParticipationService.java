package com.huertos.comunidad_huertos_api.services;

import java.util.List;
import java.util.UUID;

import com.huertos.comunidad_huertos_api.model.DTO.ParticipationDTO.ParticipationRequestDTO;
import com.huertos.comunidad_huertos_api.model.DTO.ParticipationDTO.ParticipationResponseDTO;

public interface ParticipationService {

	ParticipationResponseDTO createParticipation(ParticipationRequestDTO participation);

	ParticipationResponseDTO updateParticipation(UUID id, ParticipationRequestDTO participation);

	List<ParticipationResponseDTO> findAll();

	ParticipationResponseDTO findById(UUID id);

	void deleteById(UUID id);
}
