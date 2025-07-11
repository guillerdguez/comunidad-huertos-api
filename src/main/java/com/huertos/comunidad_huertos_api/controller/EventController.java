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

import com.huertos.comunidad_huertos_api.model.DTO.EventDTO.EventRequestDTO;
import com.huertos.comunidad_huertos_api.model.DTO.EventDTO.EventResponseDTO;
import com.huertos.comunidad_huertos_api.services.EventService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/events")
public class EventController {

	private final EventService service;

	public EventController(EventService service) {
		this.service = service;
	}

	@PostMapping
	@Operation(summary = "Create a new Event")
	public ResponseEntity<EventResponseDTO> create(@RequestBody EventRequestDTO event) {

		EventResponseDTO eventResponseDTO = service.createEvent(event);

		return ResponseEntity.ok().body(eventResponseDTO);
	}

	@GetMapping
	@Operation(summary = "Get all Events")
	public ResponseEntity<List<EventResponseDTO>> getAll() {
		List<EventResponseDTO> events = service.findAll();

		return ResponseEntity.ok().body(events);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Get Event by ID")
	public ResponseEntity<EventResponseDTO> getEvent(@PathVariable UUID id) {
		return ResponseEntity.ok().body(service.findById(id));
	}

	@PutMapping("/{id}")
	@Operation(summary = "Update a new Event")
	public ResponseEntity<EventResponseDTO> update(@PathVariable UUID id,
			@Valid @RequestBody EventRequestDTO eventRequest) {

		EventResponseDTO eventResponseDTO = service.updateEvent(id, eventRequest);
		return ResponseEntity.ok().body(eventResponseDTO);

	}

	@DeleteMapping("/{id}")

	@Operation(summary = "Delete a Event")
	public ResponseEntity<Void> delete(@PathVariable UUID id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();

	}
}
