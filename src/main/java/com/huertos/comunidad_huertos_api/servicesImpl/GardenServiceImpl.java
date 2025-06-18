package com.huertos.comunidad_huertos_api.servicesImpl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.huertos.comunidad_huertos_api.model.Garden;
import com.huertos.comunidad_huertos_api.repository.GardenRepository;
import com.huertos.comunidad_huertos_api.services.GardenService;

public class GardenServiceImpl implements GardenService {
	private final GardenRepository repository;

	public GardenServiceImpl(GardenRepository repository) {

		this.repository = repository;
	}

	@Override
	public Garden save(Garden garden) {

		return repository.save(garden);
	}

	@Override
	public List<Garden> findAll() {

		return (List<Garden>) repository.findAll();
	}

	@Override
	public Optional<Garden> findById(UUID id) {

		return repository.findById(id);
	}

	@Override
	public void deleteById(UUID id) {
		repository.deleteById(id);
	}

}
