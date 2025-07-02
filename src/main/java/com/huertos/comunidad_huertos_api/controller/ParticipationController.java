package com.huertos.comunidad_huertos_api.controller;

import com.huertos.comunidad_huertos_api.DTO.ParticipationDTO.ParticipationRequestDTO;
import com.huertos.comunidad_huertos_api.DTO.ParticipationDTO.ParticipationResponseDTO;
import com.huertos.comunidad_huertos_api.services.ParticipationService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/participations")
public class ParticipationController {

    private final ParticipationService service;

    public ParticipationController(ParticipationService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Create a new Participation")
    public ResponseEntity<ParticipationResponseDTO> create(@RequestBody ParticipationRequestDTO participation) {

        ParticipationResponseDTO participationResponseDTO = service.createParticipation(participation);

        return ResponseEntity.ok().body(participationResponseDTO);
    }

    @GetMapping
    @Operation(summary = "Get all Participations")
    public ResponseEntity<List<ParticipationResponseDTO>> getAll() {
        List<ParticipationResponseDTO> participations = service.findAll();

        return ResponseEntity.ok().body(participations);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Participation by ID")
    public ResponseEntity<ParticipationResponseDTO> getParticipation(@PathVariable UUID id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a new Participation")
    public ResponseEntity<ParticipationResponseDTO> update(@PathVariable UUID id,
                                                           @Valid @RequestBody ParticipationRequestDTO participationRequest) {

        ParticipationResponseDTO participationResponseDTO = service.updateParticipation(id, participationRequest);
        return ResponseEntity.ok().body(participationResponseDTO);

    }

    @DeleteMapping("/{id}")

    @Operation(summary = "Delete a Participation")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();

    }
}
