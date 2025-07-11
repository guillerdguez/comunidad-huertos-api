package com.huertos.comunidad_huertos_api.mapper;

import java.time.LocalDateTime;

import com.huertos.comunidad_huertos_api.model.DTO.GardenDTO.GardenRequestDTO;
import com.huertos.comunidad_huertos_api.model.DTO.GardenDTO.GardenResponseDTO;
import com.huertos.comunidad_huertos_api.model.entity.Garden;

public class GardenMapper {

	public static GardenResponseDTO toDTO(Garden g) {
		GardenResponseDTO dto = new GardenResponseDTO();
		dto.setId(g.getId());
		dto.setName(g.getName());
		dto.setLocation(g.getLocation());
		dto.setDescription(g.getDescription());
		dto.setCreatedAt(g.getCreatedAt());
		return dto;
	}

	public static Garden toModel(GardenRequestDTO dto) {
		Garden g = new Garden();
		g.setName(dto.getName());
		g.setLocation(dto.getLocation());
		g.setDescription(dto.getDescription());
		g.setCreatedAt(LocalDateTime.now());
		return g;
	}

}
