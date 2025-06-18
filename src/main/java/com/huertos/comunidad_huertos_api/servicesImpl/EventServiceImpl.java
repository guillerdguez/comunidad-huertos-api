package com.huertos.comunidad_huertos_api.servicesImpl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; 

import com.huertos.comunidad_huertos_api.model.Event;
import com.huertos.comunidad_huertos_api.repository.EventRepository;
import com.huertos.comunidad_huertos_api.services.EventService;

@Service
@Transactional
public class EventServiceImpl implements EventService {

	private static final Logger log = LoggerFactory.getLogger(EventServiceImpl.class);
	private final EventRepository repository;

	public EventServiceImpl(EventRepository repository) {
		this.repository = repository;
	}

	@Override
	public Event save(Event event) {
		log.debug("Guardando evento: {}", event);
		return repository.save(event);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Event> findAll() {
		log.debug("Recuperando todos los eventos");
		return (List<Event>) repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Event> findById(UUID id) {
		log.debug("Buscando evento por ID: {}", id);
		return repository.findById(id);
	}

	@Override
	public void deleteById(UUID id) {
		log.debug("Eliminando evento por ID: {}", id);
		repository.deleteById(id);
	}
}
