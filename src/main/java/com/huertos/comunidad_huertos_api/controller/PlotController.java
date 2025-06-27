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

import com.huertos.comunidad_huertos_api.model.Plot;
import com.huertos.comunidad_huertos_api.services.PlotService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/plots")
public class PlotController {

	private final PlotService service;

	public PlotController(PlotService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<Plot> create(@Valid @RequestBody Plot event) {
		Plot saved = service.save(event);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getId()).toUri();
		return ResponseEntity.created(uri).body(saved);
	}

	@GetMapping
	public ResponseEntity<List<Plot>> getAll() {
		return ResponseEntity.ok(service.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Plot> getById(@PathVariable UUID id) {
		return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<Plot> update(@PathVariable UUID id, @Valid @RequestBody Plot event) {
		return service.findById(id).map(existing -> {
			event.setId(id);
			return ResponseEntity.ok(service.save(event));
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable UUID id) {
		Optional<Plot> userOpt = service.findById(id);
		if (userOpt.isPresent()) {
			service.deleteById(id);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
