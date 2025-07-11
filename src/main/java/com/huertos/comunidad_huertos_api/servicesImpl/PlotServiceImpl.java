package com.huertos.comunidad_huertos_api.servicesImpl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huertos.comunidad_huertos_api.exception.GardenNotFoundException;
import com.huertos.comunidad_huertos_api.exception.PlotNotFoundException;
import com.huertos.comunidad_huertos_api.mapper.PlotMapper;
import com.huertos.comunidad_huertos_api.model.DTO.PlotDTO.PlotRequestDTO;
import com.huertos.comunidad_huertos_api.model.DTO.PlotDTO.PlotResponseDTO;
import com.huertos.comunidad_huertos_api.model.entity.Garden;
import com.huertos.comunidad_huertos_api.model.entity.Plot;
import com.huertos.comunidad_huertos_api.repository.GardenRepository;
import com.huertos.comunidad_huertos_api.repository.PlotRepository;
import com.huertos.comunidad_huertos_api.services.PlotService;

@Service
@Transactional
public class PlotServiceImpl implements PlotService {

	private static final Logger log = LoggerFactory.getLogger(PlotServiceImpl.class);
	private final PlotRepository repository;
	private final GardenRepository gardenRepo;

	public PlotServiceImpl(PlotRepository repository, GardenRepository gardenRepo) {
		this.repository = repository;
		this.gardenRepo = gardenRepo;
	}

	@Override
	public PlotResponseDTO createPlot(PlotRequestDTO dto) {
		Garden garden = gardenRepo.findById(dto.getGardenId())
				.orElseThrow(() -> new GardenNotFoundException("No existe jardín con ID: " + dto.getGardenId()));

		Plot plot = PlotMapper.toModel(dto);
		plot.setGarden(garden);

		Plot saved = repository.save(plot);
		return PlotMapper.toDTO(saved);
	}

	@Override
	public PlotResponseDTO updatePlot(UUID id, PlotRequestDTO plotRequest) {
		Plot plot = repository.findById(id)
				.orElseThrow(() -> new PlotNotFoundException("Plot not found with ID: " + id));

		plot.setSizeM2(plotRequest.getSizeM2());
		plot.setSoilType(plotRequest.getSoilType());
		plot.setActive(plotRequest.getIsActive());

		Plot updatedPlot = repository.save(plot);
		return PlotMapper.toDTO(updatedPlot);
	}

	@Override
	@Transactional(readOnly = true)
	public List<PlotResponseDTO> findAll() {
		log.debug("Recuperando todos los plots");

		List<Plot> plots = (List<Plot>) repository.findAll();

		return plots.stream().map(PlotMapper::toDTO).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public PlotResponseDTO findById(UUID id) {
		Plot plot = repository.findById(id)
				.orElseThrow(() -> new PlotNotFoundException("User not found with ID: " + id));
		return PlotMapper.toDTO(plot);
	}

	@Override
	public void deleteById(UUID id) {
		log.debug("Eliminando jardin por ID: {}", id);
		repository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<PlotResponseDTO> findByGardenId(UUID gardenId) {
		Garden garden = gardenRepo.findById(gardenId)
				.orElseThrow(() -> new GardenNotFoundException("No existe jardín con ID: " + gardenId));

		List<Plot> plots = repository.findByGardenId(gardenId);

		return plots.stream().map(PlotMapper::toDTO).collect(Collectors.toList());
	}

	@Override
	public List<PlotResponseDTO> findByOwnerIsNull() {
		List<Plot> plots = repository.findByOwnerIsNull();

		return plots.stream().map(PlotMapper::toDTO).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public List<PlotResponseDTO> findByGardenIdAndOwnerIsNull(UUID gardenId) {
		List<Plot> plots = repository.findByGardenIdAndOwnerIsNull(gardenId);

		return plots.stream().map(PlotMapper::toDTO).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public List<PlotResponseDTO> findByActiveAndSoilType(String soilType) {
		List<Plot> plots = repository.findByActiveTrueAndSoilType(soilType);

		return plots.stream().map(PlotMapper::toDTO).collect(Collectors.toList());
	}
}
