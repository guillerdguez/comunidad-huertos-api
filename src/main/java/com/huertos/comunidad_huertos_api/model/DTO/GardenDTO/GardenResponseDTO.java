package com.huertos.comunidad_huertos_api.model.DTO.GardenDTO;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GardenResponseDTO {

	private UUID id;
	private String name;
	private String location;
	private String description;
	private LocalDateTime createdAt;

}
