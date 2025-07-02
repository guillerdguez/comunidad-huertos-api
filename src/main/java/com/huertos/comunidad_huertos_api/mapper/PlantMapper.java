package com.huertos.comunidad_huertos_api.mapper;

import com.huertos.comunidad_huertos_api.DTO.PlantDTO.PlantRequestDTO;
import com.huertos.comunidad_huertos_api.DTO.PlantDTO.PlantResponseDTO;
import com.huertos.comunidad_huertos_api.model.Plant;
import com.huertos.comunidad_huertos_api.model.Plot;

public class PlantMapper {

	public static Plant toEntity(PlantRequestDTO dto) {
		Plant plant = new Plant();
		plant.setSpecies(dto.getSpecies());
		plant.setPlantedAt(dto.getPlantedAt());
		plant.setHarvestDate(dto.getHarvestDate());
		plant.setStatus(dto.getStatus());
		Plot plot = new Plot();
		plot.setId(dto.getPlotId());
		plant.setPlot(plot);
		return plant;
	}

	public static PlantResponseDTO toDto(Plant plant) {
		PlantResponseDTO dto = new PlantResponseDTO();
		dto.setId(plant.getId());
		dto.setSpecies(plant.getSpecies());
		dto.setPlantedAt(plant.getPlantedAt());
		dto.setHarvestDate(plant.getHarvestDate());
		dto.setStatus(plant.getStatus());
		dto.setPlotId(plant.getPlot().getId());
		return dto;
	}
}
