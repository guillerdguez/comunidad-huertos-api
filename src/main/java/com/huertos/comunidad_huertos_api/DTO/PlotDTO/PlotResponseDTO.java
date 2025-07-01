
package com.huertos.comunidad_huertos_api.DTO.PlotDTO;

import java.util.UUID;

public class PlotResponseDTO {

	private UUID id;
	private Long sizeM2;
	private String soilType;
	private Boolean isActive;
	private UUID gardenId;

	public UUID getGardenId() {
		return gardenId;
	}

	public void setGardenId(UUID gardenId) {
		this.gardenId = gardenId;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
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
