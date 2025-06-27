package com.huertos.comunidad_huertos_api.servicesImpl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huertos.comunidad_huertos_api.model.Garden;
import com.huertos.comunidad_huertos_api.model.Plot;
import com.huertos.comunidad_huertos_api.repository.GardenRepository;
import com.huertos.comunidad_huertos_api.services.GardenService;

@Service
@Transactional
public class GardenServiceImpl implements GardenService {

	private static final Logger log = LoggerFactory.getLogger(GardenServiceImpl.class);
	private final GardenRepository repository;

	public GardenServiceImpl(GardenRepository repository) {
		this.repository = repository;
	}

	@Override
	public Garden save(Garden garden) {
		log.debug("Guardando jardin: {}", garden);
		return repository.save(garden);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Garden> findAll() {
		log.debug("Recuperando todos los jardines");
		return (List<Garden>) repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Garden> findById(UUID id) {
		log.debug("Buscando jardin por ID: {}", id);
		return repository.findById(id);
	}

	@Override
	public void deleteById(UUID id) {
		log.debug("Eliminando jardin por ID: {}", id);
		repository.deleteById(id);
	}

	// other features
	@Override
	@Transactional(readOnly = true)
	// revisar
	public Optional<List<Plot>> findPlotsByGardenId(UUID id) {
		return repository.findById(id).map(Garden::getPlots);
	}

}
