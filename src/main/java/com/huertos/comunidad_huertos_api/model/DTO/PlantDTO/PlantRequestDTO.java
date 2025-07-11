package com.huertos.comunidad_huertos_api.model.DTO.PlantDTO;

import java.util.UUID;

import com.huertos.comunidad_huertos_api.model.entity.enums.PlantStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlantRequestDTO {

	@NotBlank
	private String species;

	@NotNull
	private String plantedAt;

	private String harvestDate;

	private PlantStatus status;

	@NotNull
	private UUID plotId;

}
