package com.huertos.comunidad_huertos_api.DTO.GardenDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class GardenRequestDTO {

	@NotBlank(message = "El nombre es obligatorio")
	@Size(max = 50, message = "El nombre no puede exceder 50 caracteres")
	private String name;

	@NotBlank(message = "La ubicación es obligatoria")
	@Size(max = 50, message = "La ubicación no puede exceder 50 caracteres")
	private String location;

	@NotBlank(message = "La descripción es obligatoria")
	@Size(max = 50, message = "La descripción no puede exceder 50 caracteres")
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}