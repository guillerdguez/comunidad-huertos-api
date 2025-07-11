package com.huertos.comunidad_huertos_api.mapper;

import com.huertos.comunidad_huertos_api.model.DTO.ParticipationDTO.ParticipationRequestDTO;
import com.huertos.comunidad_huertos_api.model.DTO.ParticipationDTO.ParticipationResponseDTO;
import com.huertos.comunidad_huertos_api.model.entity.Event;
import com.huertos.comunidad_huertos_api.model.entity.Participation;
import com.huertos.comunidad_huertos_api.model.entity.User;

public class ParticipationMapper {
	public static ParticipationResponseDTO toDTO(Participation participation) {
		ParticipationResponseDTO dto = new ParticipationResponseDTO();
		dto.setId(participation.getId());
		dto.setStatus(participation.getStatus());
		dto.setEventId(participation.getEvent().getId());
		dto.setUserId(participation.getUser().getId());
		return dto;
	}

	public static Participation toModel(ParticipationRequestDTO dto) {
		Participation participation = new Participation();
		participation.setStatus(dto.getStatus());
		Event event = new Event();
		event.setId(dto.getEventId());
		participation.setEvent(event);
		User user = new User();
		user.setId(dto.getUserId());
		participation.setUser(user);
		return participation;
	}

}
