package com.huertos.comunidad_huertos_api.model.DTO.GardenDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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

}