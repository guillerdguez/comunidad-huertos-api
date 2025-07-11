package com.huertos.comunidad_huertos_api.model.DTO.PlantDTO;

import java.util.UUID;

import com.huertos.comunidad_huertos_api.model.entity.enums.PlantStatus;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlantResponseDTO {

	private UUID id;
	private String species;
	private String plantedAt;
	private String harvestDate;
	private PlantStatus status;
	private UUID plotId;

}
