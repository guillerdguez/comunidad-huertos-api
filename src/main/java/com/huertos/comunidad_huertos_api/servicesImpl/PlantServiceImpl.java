package com.huertos.comunidad_huertos_api.servicesImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huertos.comunidad_huertos_api.exception.PlantNotFoundException;
import com.huertos.comunidad_huertos_api.exception.PlotNotFoundException;
import com.huertos.comunidad_huertos_api.mapper.PlantMapper;
import com.huertos.comunidad_huertos_api.model.DTO.PlantDTO.PlantRequestDTO;
import com.huertos.comunidad_huertos_api.model.DTO.PlantDTO.PlantResponseDTO;
import com.huertos.comunidad_huertos_api.model.entity.Plant;
import com.huertos.comunidad_huertos_api.model.entity.Plot;
import com.huertos.comunidad_huertos_api.repository.PlantRepository;
import com.huertos.comunidad_huertos_api.repository.PlotRepository;
import com.huertos.comunidad_huertos_api.services.PlantService;

@Service
@Transactional
public class PlantServiceImpl implements PlantService {

	private static final Logger log = LoggerFactory.getLogger(PlantServiceImpl.class);
	private final PlantRepository repository;
	private final PlotRepository plotRepository;

	public PlantServiceImpl(PlantRepository repository, PlotRepository plotRepository) {
		this.repository = repository;
		this.plotRepository = plotRepository;
	}

	@Override
	public PlantResponseDTO createPlant(PlantRequestDTO plant) {

		log.debug("Guardando plant: {}", plant);

		Plant newPlant = repository.save(PlantMapper.toModel(plant));
		return PlantMapper.toDTO(newPlant);
	}

	@Override
	public PlantResponseDTO updatePlant(UUID id, PlantRequestDTO plantRequest) {
		Plant plant = repository.findById(id)
				.orElseThrow(() -> new PlantNotFoundException("Plant not found with ID: " + id));

		plant.setSpecies(plantRequest.getSpecies());
		plant.setStatus(plantRequest.getStatus());
		plant.setPlantedAt(
				LocalDateTime.parse(plantRequest.getPlantedAt(), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
		plant.setHarvestDate(
				LocalDateTime.parse(plantRequest.getHarvestDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
		Plot plot = plotRepository.findById(plantRequest.getPlotId())
				.orElseThrow(() -> new PlotNotFoundException("Plot not found with ID: " + plantRequest.getPlotId()));
		plant.setPlot(plot);
		Plant updatedPlant = repository.save(plant);

		return PlantMapper.toDTO(updatedPlant);
	}

	@Override
	@Transactional(readOnly = true)
	public List<PlantResponseDTO> findAll() {
		log.debug("Recuperando todos los plants");

		List<Plant> plants = (List<Plant>) repository.findAll();

		return plants.stream().map(PlantMapper::toDTO).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public PlantResponseDTO findById(UUID id) {
		Plant plant = repository.findById(id)
				.orElseThrow(() -> new PlantNotFoundException("Plant not found with ID: " + id));
		return PlantMapper.toDTO(plant);
	}

	@Override
	public void deleteById(UUID id) {
		log.debug("Eliminando plant por ID: {}", id);
		repository.deleteById(id);
	}
}
