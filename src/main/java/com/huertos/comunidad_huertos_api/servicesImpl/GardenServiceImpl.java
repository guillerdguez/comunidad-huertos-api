package com.huertos.comunidad_huertos_api.servicesImpl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huertos.comunidad_huertos_api.exception.GardenNotFoundException;
import com.huertos.comunidad_huertos_api.mapper.GardenMapper;
import com.huertos.comunidad_huertos_api.model.DTO.GardenDTO.GardenRequestDTO;
import com.huertos.comunidad_huertos_api.model.DTO.GardenDTO.GardenResponseDTO;
import com.huertos.comunidad_huertos_api.model.entity.Garden;
import com.huertos.comunidad_huertos_api.model.entity.Plot;
import com.huertos.comunidad_huertos_api.repository.GardenRepository;
import com.huertos.comunidad_huertos_api.services.GardenService;

@Service
@Transactional
public class GardenServiceImpl implements GardenService {

	private static final Logger log = LoggerFactory.getLogger(GardenServiceImpl.class);
	private final GardenRepository repository;

	public GardenServiceImpl(GardenRepository repository) {
		this.repository = repository;
	}

	@Override
	public GardenResponseDTO createGarden(GardenRequestDTO garden) {

		Garden newGarden = repository.save(GardenMapper.toModel(garden));

		return GardenMapper.toDTO(newGarden);
	}

	@Override
	public GardenResponseDTO updateGarden(UUID id, GardenRequestDTO gardenRequestDTO) {
		Garden garden = repository.findById(id)
				.orElseThrow(() -> new GardenNotFoundException("Garden not found with ID: " + id));

//		if (repository.existsByNameAndIdNot(gardenRequestDTO.getName(), id)) {
//			throw new GardenNameAlreadyExistsException(
//					"A garden with this name already exists: " + gardenRequestDTO.getName());
//		}

		garden.setName(gardenRequestDTO.getName());
		garden.setLocation(gardenRequestDTO.getLocation());
		garden.setDescription(gardenRequestDTO.getDescription());

		Garden updatedGarden = repository.save(garden);
		return GardenMapper.toDTO(updatedGarden);
	}

	@Override
	@Transactional(readOnly = true)
	public List<GardenResponseDTO> findAll() {
		List<Garden> gardens = repository.findAll();

		return gardens.stream().map(GardenMapper::toDTO).toList();
	}

	@Override
	@Transactional(readOnly = true)
	public GardenResponseDTO findById(UUID id) {
		Garden garden = repository.findById(id)
				.orElseThrow(() -> new GardenNotFoundException("Garden not found with ID: " + id));
		return GardenMapper.toDTO(garden);
	}

	@Override
	public void deleteById(UUID id) {
		log.debug("Eliminando jardin por ID: {}", id);
		repository.deleteById(id);
	}

	// other features
	@Override
	@Transactional(readOnly = true)
	// revisar
	public Optional<List<Plot>> findPlotsByGardenId(UUID id) {
		return repository.findById(id).map(Garden::getPlots);
	}

}
