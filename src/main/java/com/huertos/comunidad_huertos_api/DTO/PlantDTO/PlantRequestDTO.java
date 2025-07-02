package com.huertos.comunidad_huertos_api.DTO.PlantDTO;

import com.huertos.comunidad_huertos_api.model.enums.PlantStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class PlantRequestDTO {

    @NotBlank
    private String species;

    @NotNull
    private String plantedAt;

    private String harvestDate;

    private PlantStatus status;

    @NotNull
    private UUID plotId;

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getPlantedAt() {
        return plantedAt;
    }

    public void setPlantedAt(String plantedAt) {
        this.plantedAt = plantedAt;
    }

    public String getHarvestDate() {
        return harvestDate;
    }

    public void setHarvestDate(String harvestDate) {
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
