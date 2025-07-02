package com.huertos.comunidad_huertos_api.mapper;

import com.huertos.comunidad_huertos_api.DTO.EventDTO.EventRequestDTO;
import com.huertos.comunidad_huertos_api.DTO.EventDTO.EventResponseDTO;
import com.huertos.comunidad_huertos_api.model.Event;
import com.huertos.comunidad_huertos_api.model.Garden;
import com.huertos.comunidad_huertos_api.model.User;

public class EventMapper {

	public static Event toEntity(EventRequestDTO dto) {
		if (dto == null) {
			return null;
		}

		Event event = new Event();
		event.setTitle(dto.getTitle());
		event.setDescription(dto.getDescription());
		event.setEventDate(dto.getEventDate());

		Garden garden = new Garden();
		garden.setId(dto.getGardenId());
		event.setGarden(garden);

		User creator = new User();
		creator.setId(dto.getCreatedById());
		event.setCreatedBy(creator);

		return event;
	}

	public static EventResponseDTO toDto(Event event) {
		if (event == null) {
			return null;
		}

		EventResponseDTO dto = new EventResponseDTO();
		dto.setId(event.getId());
		dto.setTitle(event.getTitle());
		dto.setDescription(event.getDescription());
		dto.setEventDate(event.getEventDate());
		dto.setGardenId(event.getGarden().getId());
		dto.setCreatedById(event.getCreatedBy().getId());

		return dto;
	}
}
