package com.huertos.comunidad_huertos_api.services;

import java.util.List;
import java.util.UUID;

import com.huertos.comunidad_huertos_api.DTO.PlotDTO.PlotRequestDTO;
import com.huertos.comunidad_huertos_api.DTO.PlotDTO.PlotResponseDTO;

public interface PlotService {
	PlotResponseDTO createPlot(PlotRequestDTO plot);

	PlotResponseDTO updatePlot(UUID id, PlotRequestDTO user);

	List<PlotResponseDTO> findAll();

	PlotResponseDTO findById(UUID id);

	void deleteById(UUID id);

}
