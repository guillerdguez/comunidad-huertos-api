package com.huertos.comunidad_huertos_api.servicesImpl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huertos.comunidad_huertos_api.model.Plot;
import com.huertos.comunidad_huertos_api.repository.PlotRepository;
import com.huertos.comunidad_huertos_api.services.PlotService;

@Service
@Transactional
public class PlotServiceImpl implements PlotService {

	private static final Logger log = LoggerFactory.getLogger(PlotServiceImpl.class);
	private final PlotRepository repository;

	public PlotServiceImpl(PlotRepository repository) {
		this.repository = repository;
	}

	@Override
	public Plot save(Plot garden) {
		log.debug("Guardando jardin: {}", garden);
		return repository.save(garden);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Plot> findAll() {
		log.debug("Recuperando todos los jardines");
		return (List<Plot>) repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Plot> findById(UUID id) {
		log.debug("Buscando jardin por ID: {}", id);
		return repository.findById(id);
	}

	@Override
	public void deleteById(UUID id) {
		log.debug("Eliminando jardin por ID: {}", id);
		repository.deleteById(id);
	}
}
