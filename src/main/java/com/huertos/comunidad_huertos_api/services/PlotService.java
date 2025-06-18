package com.huertos.comunidad_huertos_api.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.huertos.comunidad_huertos_api.model.Plot;

public interface PlotService {
	Plot save(Plot plot);

	List<Plot> findAll();

	Optional<Plot> findById(UUID id);

	void deleteById(UUID id);

}
