package com.huertos.comunidad_huertos_api.services;

import com.huertos.comunidad_huertos_api.DTO.ParticipationDTO.ParticipationRequestDTO;
import com.huertos.comunidad_huertos_api.DTO.ParticipationDTO.ParticipationResponseDTO;

import java.util.List;
import java.util.UUID;

public interface ParticipationService {

    ParticipationResponseDTO createParticipation(ParticipationRequestDTO participation);

    ParticipationResponseDTO updateParticipation(UUID id, ParticipationRequestDTO participation);

    List<ParticipationResponseDTO> findAll();

    ParticipationResponseDTO findById(UUID id);

    void deleteById(UUID id);
}
