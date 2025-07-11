package com.huertos.comunidad_huertos_api.model.DTO.PlotDTO;

import java.util.UUID;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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

}
