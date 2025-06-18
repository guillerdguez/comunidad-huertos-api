package com.huertos.comunidad_huertos_api.model.DTO.GardenDTO;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UpdateGardenDTO {

	@NotNull(message = "El ID del huerto es obligatorio")
	private UUID id;

	@NotBlank(message = "El nombre es obligatorio")
	private String name;

	@NotBlank(message = "La ubicación es obligatoria")
	private String location;

	@NotBlank(message = "La descripción es obligatoria")
	private String description;

	public UpdateGardenDTO() {
	}

	public UpdateGardenDTO(UUID id, String name, String location, String description) {
		this.id = id;
		this.name = name;
		this.location = location;
		this.description = description;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
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
