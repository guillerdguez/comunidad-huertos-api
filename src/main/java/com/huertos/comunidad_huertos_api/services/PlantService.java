package com.huertos.comunidad_huertos_api.services;

import java.util.List;
import java.util.UUID;

import com.huertos.comunidad_huertos_api.model.DTO.PlantDTO.PlantRequestDTO;
import com.huertos.comunidad_huertos_api.model.DTO.PlantDTO.PlantResponseDTO;

public interface PlantService {

	PlantResponseDTO createPlant(PlantRequestDTO plant);

	PlantResponseDTO updatePlant(UUID id, PlantRequestDTO plant);

	List<PlantResponseDTO> findAll();

	PlantResponseDTO findById(UUID id);

	void deleteById(UUID id);

}
