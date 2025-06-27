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

import com.huertos.comunidad_huertos_api.model.Participation;
import com.huertos.comunidad_huertos_api.services.ParticipationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/participations")
public class ParticipationController {

	private final ParticipationService service;

	public ParticipationController(ParticipationService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<Participation> create(@Valid @RequestBody Participation Participation) {
		Participation saved = service.save(Participation);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getId()).toUri();
		return ResponseEntity.created(uri).body(saved);
	}

	@GetMapping
	public ResponseEntity<List<Participation>> getAll() {
		return ResponseEntity.ok(service.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Participation> getById(@PathVariable UUID id) {
		return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<Participation> update(@PathVariable UUID id,
			@Valid @RequestBody Participation Participation) {
		return service.findById(id).map(existing -> {
			Participation.setId(id);
			return ResponseEntity.ok(service.save(Participation));
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable UUID id) {
		Optional<Participation> userOpt = service.findById(id);
		if (userOpt.isPresent()) {
			service.deleteById(id);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
