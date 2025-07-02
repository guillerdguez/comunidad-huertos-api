package com.huertos.comunidad_huertos_api.DTO.PlantDTO;

import java.time.LocalDateTime;
import java.util.UUID;

import com.huertos.comunidad_huertos_api.model.enums.PlantStatus;

public class PlantResponseDTO {

	private UUID id;
	private String species;
	private LocalDateTime plantedAt;
	private LocalDateTime harvestDate;
	private PlantStatus status;
	private UUID plotId;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public LocalDateTime getPlantedAt() {
		return plantedAt;
	}

	public void setPlantedAt(LocalDateTime plantedAt) {
		this.plantedAt = plantedAt;
	}

	public LocalDateTime getHarvestDate() {
		return harvestDate;
	}

	public void setHarvestDate(LocalDateTime harvestDate) {
		this.harvestDate = harvestDate;
	}

	public PlantStatus getStatus() {
		return status;
	}

	public void setStatus(PlantStatus status) {
		this.status = status;
	}

	public UUID getPlotId() {
		return plotId;
	}

	public void setPlotId(UUID plotId) {
		this.plotId = plotId;
	}
}
