package com.huertos.comunidad_huertos_api.servicesImpl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huertos.comunidad_huertos_api.model.Plant;
import com.huertos.comunidad_huertos_api.repository.PlantRepository;
import com.huertos.comunidad_huertos_api.services.PlantService;

@Service
@Transactional
public class PlantServiceImpl implements PlantService {

	private static final Logger log = LoggerFactory.getLogger(PlantServiceImpl.class);
	private final PlantRepository repository;

	public PlantServiceImpl(PlantRepository repository) {
		this.repository = repository;
	}

	@Override
	public Plant save(Plant garden) {
		log.debug("Guardando planta: {}", garden);
		return repository.save(garden);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Plant> findAll() {
		log.debug("Recuperando todos los plantas");
		return (List<Plant>) repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Plant> findById(UUID id) {
		log.debug("Buscando planta por ID: {}", id);
		return repository.findById(id);
	}

	@Override
	public void deleteById(UUID id) {
		log.debug("Eliminando planta por ID: {}", id);
		repository.deleteById(id);
	}
}
