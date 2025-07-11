package com.huertos.comunidad_huertos_api.servicesImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huertos.comunidad_huertos_api.exception.EventNotFoundException;
import com.huertos.comunidad_huertos_api.exception.GardenNotFoundException;
import com.huertos.comunidad_huertos_api.mapper.EventMapper;
import com.huertos.comunidad_huertos_api.model.DTO.EventDTO.EventRequestDTO;
import com.huertos.comunidad_huertos_api.model.DTO.EventDTO.EventResponseDTO;
import com.huertos.comunidad_huertos_api.model.entity.Event;
import com.huertos.comunidad_huertos_api.model.entity.Garden;
import com.huertos.comunidad_huertos_api.repository.EventRepository;
import com.huertos.comunidad_huertos_api.repository.GardenRepository;
import com.huertos.comunidad_huertos_api.services.EventService;

@Service
@Transactional
public class EventServiceImpl implements EventService {

	private static final Logger log = LoggerFactory.getLogger(EventServiceImpl.class);
	private final EventRepository repository;
	private final GardenRepository gardenRepository;

	public EventServiceImpl(EventRepository repository, GardenRepository gardenRepository) {
		this.repository = repository;
		this.gardenRepository = gardenRepository;
	}

	@Override
	public EventResponseDTO createEvent(EventRequestDTO event) {

		log.debug("Guardando event: {}", event);

		Event newEvent = repository.save(EventMapper.toModel(event));
		return EventMapper.toDTO(newEvent);
	}

	@Override
	public EventResponseDTO updateEvent(UUID id, EventRequestDTO eventRequest) {
		Event event = repository.findById(id)
				.orElseThrow(() -> new EventNotFoundException("Event not found with ID: " + id));

		event.setTitle(eventRequest.getTitle());
		event.setDescription(eventRequest.getDescription());
		event.setEventDate(
				LocalDateTime.parse(eventRequest.getEventDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
		Garden garden = gardenRepository.findById(eventRequest.getGardenId()).orElseThrow(
				() -> new GardenNotFoundException("Garden not found with ID: " + eventRequest.getGardenId()));
		event.setGarden(garden);
		Event updatedEvent = repository.save(event);

		return EventMapper.toDTO(updatedEvent);
	}

	@Override
	@Transactional(readOnly = true)
	public List<EventResponseDTO> findAll() {
		log.debug("Recuperando todos los events");

		List<Event> events = (List<Event>) repository.findAll();

		return events.stream().map(EventMapper::toDTO).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public EventResponseDTO findById(UUID id) {
		Event event = repository.findById(id)
				.orElseThrow(() -> new EventNotFoundException("Event not found with ID: " + id));
		return EventMapper.toDTO(event);
	}

	@Override
	public void deleteById(UUID id) {
		log.debug("Eliminando event por ID: {}", id);
		repository.deleteById(id);
	}
}
