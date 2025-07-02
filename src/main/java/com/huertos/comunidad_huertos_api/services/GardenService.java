package com.huertos.comunidad_huertos_api.services;

import com.huertos.comunidad_huertos_api.DTO.GardenDTO.GardenRequestDTO;
import com.huertos.comunidad_huertos_api.DTO.GardenDTO.GardenResponseDTO;
import com.huertos.comunidad_huertos_api.model.Plot;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GardenService {

    GardenResponseDTO createGarden(GardenRequestDTO garden);

    GardenResponseDTO updateGarden(UUID id, GardenRequestDTO garden);

    List<GardenResponseDTO> findAll();

    GardenResponseDTO findById(UUID id);

    void deleteById(UUID id);

    // other features

    Optional<List<Plot>> findPlotsByGardenId(UUID id);
}
