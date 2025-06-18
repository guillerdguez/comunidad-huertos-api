package com.huertos.comunidad_huertos_api.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.huertos.comunidad_huertos_api.model.Garden;

public interface GardenService {
	Garden save(Garden garden);

	List<Garden> findAll();

	Optional<Garden> findById(UUID id);

	void deleteById(UUID id);
}
