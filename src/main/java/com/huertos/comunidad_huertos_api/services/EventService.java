package com.huertos.comunidad_huertos_api.services;

import com.huertos.comunidad_huertos_api.DTO.EventDTO.EventRequestDTO;
import com.huertos.comunidad_huertos_api.DTO.EventDTO.EventResponseDTO;

import java.util.List;
import java.util.UUID;


public interface EventService {

    EventResponseDTO createEvent(EventRequestDTO event);

    EventResponseDTO updateEvent(UUID id, EventRequestDTO event);

    List<EventResponseDTO> findAll();

    EventResponseDTO findById(UUID id);

    void deleteById(UUID id);


}
