package com.huertos.comunidad_huertos_api.model.DTO.GardenDTO;

import jakarta.validation.constraints.NotBlank;

public class CreateGardenDTO {

	@NotBlank(message = "El nombre es obligatorio")
	private String name;

	@NotBlank(message = "La ubicación es obligatoria")
	private String location;

	@NotBlank(message = "La descripción es obligatoria")
	private String description;

	public CreateGardenDTO() {
	}

	public CreateGardenDTO(String name, String location, String description) {
		this.name = name;
		this.location = location;
		this.description = description;
	}

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
