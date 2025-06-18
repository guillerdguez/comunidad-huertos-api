package com.huertos.comunidad_huertos_api.servicesImpl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.huertos.comunidad_huertos_api.model.Garden;
import com.huertos.comunidad_huertos_api.services.GardenService;

public class GardenServiceImpl implements GardenService {

	@Override
	public Garden save(Garden garden) {

		return null;
	}

	@Override
	public List<Garden> findAll() {

		return null;
	}

	@Override
	public Optional<Garden> findById(UUID id) {

		return Optional.empty();
	}

	@Override
	public void deleteById(UUID id) {

	}

}
