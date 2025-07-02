package com.huertos.comunidad_huertos_api.DTO.PlantDTO;

import java.time.LocalDateTime;
import java.util.UUID;

import com.huertos.comunidad_huertos_api.model.enums.PlantStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PlantRequestDTO {

	@NotBlank
	private String species;

	@NotNull
	private LocalDateTime plantedAt;

	private LocalDateTime harvestDate;

	private PlantStatus status;

	@NotNull
	private UUID plotId;

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
