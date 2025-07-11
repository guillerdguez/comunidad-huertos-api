package com.huertos.comunidad_huertos_api.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.huertos.comunidad_huertos_api.model.DTO.PlotDTO.PlotRequestDTO;
import com.huertos.comunidad_huertos_api.model.DTO.PlotDTO.PlotResponseDTO;
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

	// @PostMapping
	// @Operation(summary = "Create a new Plot")
	// public ResponseEntity<PlotResponseDTO> create(@RequestBody PlotRequestDTO
	// plot) {

	// PlotResponseDTO plotResponseDTO = service.createPlot(plot);

	// return ResponseEntity.ok().body(plotResponseDTO);
	// }
	// prueba
	@PostMapping
	@Operation(summary = "Create a new Plot")
	public ResponseEntity<PlotResponseDTO> create(@Valid @RequestBody PlotRequestDTO plotRequest) {

		// 1. Crear el recurso y obtener el DTO con el ID generado
		PlotResponseDTO created = service.createPlot(plotRequest);

		// 2. Construir la URI del nuevo recurso: /plots/{id}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest() // base /plots
																		// :contentReference[oaicite:2]{index=2}
				.path("/{id}") // añade /{id} :contentReference[oaicite:3]{index=3}
				.buildAndExpand(created.getId()) // sustituye {id} por el valor real
													// :contentReference[oaicite:4]{index=4}
				.toUri(); // convierte a URI :contentReference[oaicite:5]{index=5}

		// 3. Devolver 201 Created con Location y cuerpo
		return ResponseEntity.created(location) // status 201 + header Location :contentReference[oaicite:6]{index=6}
				.body(created);
	}

	@GetMapping
	@Operation(summary = "Get all Plots")
	@ResponseStatus(HttpStatus.OK)
	public List<PlotResponseDTO> getAll() {
		return service.findAll();
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

	// extras
	@GetMapping("/garden/{gardenId}")
	@Operation(summary = "Get Plots by Garden ID")
	public ResponseEntity<List<PlotResponseDTO>> getPlotsByGardenId(@PathVariable UUID gardenId) {
		List<PlotResponseDTO> plots = service.findByGardenId(gardenId);
		return ResponseEntity.ok().body(plots);
	}

	@GetMapping("/vacant")
	@Operation(summary = "Get all Plots with owner null")
	public ResponseEntity<List<PlotResponseDTO>> getOwnerIsNull() {
		List<PlotResponseDTO> plots = service.findByOwnerIsNull();

		return ResponseEntity.ok().body(plots);
	}

	@GetMapping("/garden/{gardenId}/vacant")
	@Operation(summary = "Get Plots by Garden ID and owner null")

	public ResponseEntity<List<PlotResponseDTO>> getPlotsByGardenIdAndOwnerIsNull(@PathVariable UUID gardenId) {
		List<PlotResponseDTO> plots = service.findByGardenIdAndOwnerIsNull(gardenId);

		return ResponseEntity.ok().body(plots);
	}

	@Operation(summary = "Get active plots by soil type (path variable)")
	@GetMapping("/garden/active/soilType/{soilType}")
	public ResponseEntity<List<PlotResponseDTO>> getActiveAndSoilType(@PathVariable("soilType") String soilType) {

		List<PlotResponseDTO> plots = service.findByActiveAndSoilType(soilType);
		return ResponseEntity.ok(plots);
	}

}
