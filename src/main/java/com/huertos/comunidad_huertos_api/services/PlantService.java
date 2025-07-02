package com.huertos.comunidad_huertos_api.services;

import com.huertos.comunidad_huertos_api.DTO.PlantDTO.PlantRequestDTO;
import com.huertos.comunidad_huertos_api.DTO.PlantDTO.PlantResponseDTO;

import java.util.List;
import java.util.UUID;


public interface PlantService {

    PlantResponseDTO createPlant(PlantRequestDTO plant);

    PlantResponseDTO updatePlant(UUID id, PlantRequestDTO plant);

    List<PlantResponseDTO> findAll();

    PlantResponseDTO findById(UUID id);

    void deleteById(UUID id);


}
