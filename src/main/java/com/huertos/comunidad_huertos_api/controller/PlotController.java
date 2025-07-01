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

import com.huertos.comunidad_huertos_api.DTO.PlotDTO.PlotRequestDTO;
import com.huertos.comunidad_huertos_api.DTO.PlotDTO.PlotResponseDTO;
import com.huertos.comunidad_huertos_api.services.PlotService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/plots")
public class PlotController {

	private final PlotService service;

	public PlotController(PlotService service) {
		this.service = service;
	}

	@PostMapping
	@Operation(summary = "Create a new Plot")
	public ResponseEntity<PlotResponseDTO> create(@RequestBody PlotRequestDTO plot) {

		PlotResponseDTO plotResponseDTO = service.createPlot(plot);

		return ResponseEntity.ok().body(plotResponseDTO);
	}

	@GetMapping
	@Operation(summary = "Get all Plots")
	public ResponseEntity<List<PlotResponseDTO>> getAll() {
		List<PlotResponseDTO> plots = service.findAll();

		return ResponseEntity.ok().body(plots);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Get User by ID")
	public ResponseEntity<PlotResponseDTO> getPlotById(@PathVariable UUID id) {
		return ResponseEntity.ok().body(service.findById(id));
	}

	@PutMapping("/{id}")
	@Operation(summary = "Update a new Plot")
	public ResponseEntity<PlotResponseDTO> update(@PathVariable UUID id,
			@Valid @RequestBody PlotRequestDTO plotRequest) {

		PlotResponseDTO plotResponseDTO = service.updatePlot(id, plotRequest);
		return ResponseEntity.ok().body(plotResponseDTO);

	}

	@DeleteMapping("/{id}")

	@Operation(summary = "Delete a Plot")
	public ResponseEntity<Void> delete(@PathVariable UUID id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();

	}
}
