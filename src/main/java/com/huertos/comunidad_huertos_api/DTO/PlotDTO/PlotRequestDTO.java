package com.huertos.comunidad_huertos_api.DTO.PlotDTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class PlotRequestDTO {

    @NotNull(message = "El tamaño en m2 es obligatorio")
    @Min(value = 1, message = "El tamaño debe ser al menos 1 m2")
    private Long sizeM2;

    @NotBlank(message = "El tipo de suelo es obligatorio")
    private String soilType;

    @NotNull(message = "El estado activo es obligatorio")
    private Boolean isActive;
    @NotNull(message = "El ID del jardín es obligatorio")
    private UUID gardenId;
    private UUID ownerId;

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(UUID ownerId) {
        this.ownerId = ownerId;
    }

    public UUID getGardenId() {
        return gardenId;
    }

    public void setGardenId(UUID gardenId) {
        this.gardenId = gardenId;
    }

    public Long getSizeM2() {
        return sizeM2;
    }

    public void setSizeM2(Long sizeM2) {
        this.sizeM2 = sizeM2;
    }

    public String getSoilType() {
        return soilType;
    }

    public void setSoilType(String soilType) {
        this.soilType = soilType;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }
}
