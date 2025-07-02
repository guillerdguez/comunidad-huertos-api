package com.huertos.comunidad_huertos_api.mapper;

import com.huertos.comunidad_huertos_api.DTO.EventDTO.EventRequestDTO;
import com.huertos.comunidad_huertos_api.DTO.EventDTO.EventResponseDTO;
import com.huertos.comunidad_huertos_api.model.Event;
import com.huertos.comunidad_huertos_api.model.Garden;
import com.huertos.comunidad_huertos_api.model.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventMapper {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public static EventResponseDTO toDTO(Event event) {
        EventResponseDTO dto = new EventResponseDTO();
        dto.setId(event.getId());
        dto.setTitle(event.getTitle());
        dto.setDescription(event.getDescription());
        dto.setEventDate(event.getEventDate().format(FORMATTER));
        dto.setGardenId(event.getGarden().getId());
        dto.setCreatedById(event.getCreatedBy().getId());

        return dto;
    }

    public static Event toModel(EventRequestDTO dto) {
        Event event = new Event();
        event.setTitle(dto.getTitle());
        event.setDescription(dto.getDescription());
        LocalDateTime eventDate = LocalDateTime.parse(dto.getEventDate(), FORMATTER);
        event.setEventDate(eventDate);
        Garden garden = new Garden();
        garden.setId(dto.getGardenId());
        event.setGarden(garden);
        User creator = new User();
        creator.setId(dto.getCreatedById());
        event.setCreatedBy(creator);

        return event;
    }

}
