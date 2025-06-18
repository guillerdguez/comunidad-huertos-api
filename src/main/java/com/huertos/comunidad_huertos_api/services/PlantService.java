package com.huertos.comunidad_huertos_api.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.huertos.comunidad_huertos_api.model.Plant;

public interface PlantService {
	Plant save(Plant plant);

	List<Plant> findAll();

	Optional<Plant> findById(UUID id);

	void deleteById(UUID id);
}
