package com.huertos.comunidad_huertos_api.mapper;

import com.huertos.comunidad_huertos_api.model.DTO.PlotDTO.PlotRequestDTO;
import com.huertos.comunidad_huertos_api.model.DTO.PlotDTO.PlotResponseDTO;
import com.huertos.comunidad_huertos_api.model.entity.Garden;
import com.huertos.comunidad_huertos_api.model.entity.Plot;
import com.huertos.comunidad_huertos_api.model.entity.User;
import com.huertos.comunidad_huertos_api.services.GardenService;

public class PlotMapper {
	private final GardenService gardenService;

	public PlotMapper(GardenService gardenService) {

		this.gardenService = gardenService;
	}

	public static PlotResponseDTO toDTO(Plot plot) {
		PlotResponseDTO dto = new PlotResponseDTO();
		dto.setId(plot.getId());
		dto.setGardenId(plot.getGarden().getId());
		dto.setSizeM2(plot.getSizeM2());
		dto.setSoilType(plot.getSoilType());
		dto.setIsActive(plot.isActive());
		dto.setOwnerId(plot.getOwner() != null ? plot.getOwner().getId() : null);
		return dto;
	}

	public static Plot toModel(PlotRequestDTO dto) {
//cuando necesitas hacer referencia pero no pasarla completa en el dto
		Garden garden = new Garden();
		garden.setId(dto.getGardenId());

		Plot plot = new Plot();
		plot.setGarden(garden);
		plot.setSizeM2(dto.getSizeM2());
		plot.setSoilType(dto.getSoilType());
		plot.setActive(dto.getIsActive());
		User user = dto.getOwnerId() != null ? new User() {
			{
				setId(dto.getOwnerId());
			}
		} : null;
		plot.setOwner(user);

		return plot;
	}

}