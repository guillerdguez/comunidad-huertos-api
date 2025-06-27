package com.huertos.comunidad_huertos_api.servicesImpl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huertos.comunidad_huertos_api.model.Participation;
import com.huertos.comunidad_huertos_api.repository.ParticipationRepository;
import com.huertos.comunidad_huertos_api.services.ParticipationService;

@Service
@Transactional
public class ParticipationServiceImpl implements ParticipationService {

	private static final Logger log = LoggerFactory.getLogger(ParticipationServiceImpl.class);
	private final ParticipationRepository repository;

	public ParticipationServiceImpl(ParticipationRepository repository) {
		this.repository = repository;
	}

	@Override
	public Participation save(Participation garden) {
		log.debug("Guardando participacion: {}", garden);
		return repository.save(garden);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Participation> findAll() {
		log.debug("Recuperando todos los participaciones");
		return (List<Participation>) repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Participation> findById(UUID id) {
		log.debug("Buscando participacion por ID: {}", id);
		return repository.findById(id);
	}

	@Override
	public void deleteById(UUID id) {
		log.debug("Eliminando participacion por ID: {}", id);
		repository.deleteById(id);
	}
}
