package com.huertos.comunidad_huertos_api.mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.huertos.comunidad_huertos_api.model.DTO.PlantDTO.PlantRequestDTO;
import com.huertos.comunidad_huertos_api.model.DTO.PlantDTO.PlantResponseDTO;
import com.huertos.comunidad_huertos_api.model.entity.Plant;
import com.huertos.comunidad_huertos_api.model.entity.Plot;

public class PlantMapper {
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

	public static PlantResponseDTO toDTO(Plant plant) {
		PlantResponseDTO dto = new PlantResponseDTO();
		dto.setId(plant.getId());
		dto.setSpecies(plant.getSpecies());
		dto.setPlantedAt(plant.getPlantedAt() != null ? plant.getPlantedAt().format(FORMATTER) : null);
		dto.setHarvestDate(plant.getHarvestDate() != null ? plant.getHarvestDate().format(FORMATTER) : null);
		dto.setStatus(plant.getStatus());
		dto.setPlotId(plant.getPlot() != null ? plant.getPlot().getId() : null);
		return dto;
	}

	public static Plant toModel(PlantRequestDTO dto) {
		Plant plant = new Plant();
		plant.setSpecies(dto.getSpecies());
		LocalDateTime plantedDate = dto.getPlantedAt() != null ? LocalDateTime.parse(dto.getPlantedAt(), FORMATTER)
				: null;
		plant.setPlantedAt(plantedDate);
		LocalDateTime harvestDate = dto.getHarvestDate() != null ? LocalDateTime.parse(dto.getHarvestDate(), FORMATTER)
				: null;
		plant.setHarvestDate(harvestDate);
		plant.setStatus(dto.getStatus());
		Plot plot = new Plot();
		plot.setId(dto.getPlotId());
		plant.setPlot(plot);
		return plant;
	}
}
