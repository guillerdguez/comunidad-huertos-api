package com.huertos.comunidad_huertos_api.model.DTO.PlotDTO;

import java.util.UUID;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlotResponseDTO {

	private UUID id;
	private Long sizeM2;
	private String soilType;
	private Boolean isActive;
	private UUID gardenId;
	private UUID ownerId;

}
