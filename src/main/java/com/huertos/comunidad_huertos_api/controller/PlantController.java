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

import com.huertos.comunidad_huertos_api.model.DTO.PlantDTO.PlantRequestDTO;
import com.huertos.comunidad_huertos_api.model.DTO.PlantDTO.PlantResponseDTO;
import com.huertos.comunidad_huertos_api.services.PlantService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/plants")
public class PlantController {

	private final PlantService service;

	public PlantController(PlantService service) {
		this.service = service;
	}

	@PostMapping
	@Operation(summary = "Create a new Plant")
	public ResponseEntity<PlantResponseDTO> create(@RequestBody PlantRequestDTO plant) {

		PlantResponseDTO plantResponseDTO = service.createPlant(plant);

		return ResponseEntity.ok().body(plantResponseDTO);
	}

	@GetMapping
	@Operation(summary = "Get all Plants")
	public ResponseEntity<List<PlantResponseDTO>> getAll() {
		List<PlantResponseDTO> plants = service.findAll();

		return ResponseEntity.ok().body(plants);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Get Plant by ID")
	public ResponseEntity<PlantResponseDTO> getPlant(@PathVariable UUID id) {
		return ResponseEntity.ok().body(service.findById(id));
	}

	@PutMapping("/{id}")
	@Operation(summary = "Update a new Plant")
	public ResponseEntity<PlantResponseDTO> update(@PathVariable UUID id,
			@Valid @RequestBody PlantRequestDTO plantRequest) {

		PlantResponseDTO plantResponseDTO = service.updatePlant(id, plantRequest);
		return ResponseEntity.ok().body(plantResponseDTO);

	}

	@DeleteMapping("/{id}")

	@Operation(summary = "Delete a Plant")
	public ResponseEntity<Void> delete(@PathVariable UUID id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();

	}
}
