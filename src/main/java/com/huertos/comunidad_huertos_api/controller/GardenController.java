package com.huertos.comunidad_huertos_api.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.huertos.comunidad_huertos_api.model.Garden;
import com.huertos.comunidad_huertos_api.model.Plot;
import com.huertos.comunidad_huertos_api.services.GardenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/gardens")
public class GardenController {

	private final GardenService service;

	public GardenController(GardenService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<Garden> create(@Valid @RequestBody Garden Garden) {
		Garden saved = service.save(Garden);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getId()).toUri();
		return ResponseEntity.created(uri).body(saved);
	}

	@GetMapping
	public ResponseEntity<List<Garden>> getAll() {
		return ResponseEntity.ok(service.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Garden> getById(@PathVariable UUID id) {
		return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<Garden> update(@PathVariable UUID id, @Valid @RequestBody Garden Garden) {
		return service.findById(id).map(existing -> {
			Garden.setId(id);
			return ResponseEntity.ok(service.save(Garden));
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable UUID id) {
		Optional<Garden> userOpt = service.findById(id);
		if (userOpt.isPresent()) {
			service.deleteById(id);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// other features
	@GetMapping("/{id}/plots")
	public ResponseEntity<List<Plot>> GetPlotsByGardenId(@PathVariable UUID id) {
		// poner un if o dominar maps?
		return service.findPlotsByGardenId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

	}
}
