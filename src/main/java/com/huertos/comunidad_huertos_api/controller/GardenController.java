package com.huertos.comunidad_huertos_api.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huertos.comunidad_huertos_api.model.DTO.GardenDTO.GardenRequestDTO;
import com.huertos.comunidad_huertos_api.model.DTO.GardenDTO.GardenResponseDTO;
import com.huertos.comunidad_huertos_api.model.entity.Plot;
import com.huertos.comunidad_huertos_api.services.GardenService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/gardens")
public class GardenController {

	private final GardenService service;

	public GardenController(GardenService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<GardenResponseDTO> create(@Valid @RequestBody GardenRequestDTO Garden) {
		GardenResponseDTO gardenResponseDTO = service.createGarden(Garden);

		return ResponseEntity.ok().body(gardenResponseDTO);
	}

	@GetMapping
	public ResponseEntity<List<GardenResponseDTO>> getAll() {
		List<GardenResponseDTO> gardens = service.findAll();

		return ResponseEntity.ok().body(gardens);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Get Garden by ID")
	public ResponseEntity<GardenResponseDTO> getById(@PathVariable UUID id) {
		return ResponseEntity.ok().body(service.findById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<GardenResponseDTO> update(@PathVariable UUID id,
			@Valid @RequestBody GardenRequestDTO Garden) {

		GardenResponseDTO gardenResponseDTO = service.updateGarden(id, Garden);
		return ResponseEntity.ok().body(gardenResponseDTO);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable UUID id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	// other features
	@GetMapping("/{id}/plots")
	public ResponseEntity<List<Plot>> GetPlotsByGardenId(@PathVariable UUID id) {
		// poner un if o dominar maps?
		return service.findPlotsByGardenId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

	}
}
