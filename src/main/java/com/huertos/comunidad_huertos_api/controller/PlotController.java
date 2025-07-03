package com.huertos.comunidad_huertos_api.controller;

import com.huertos.comunidad_huertos_api.DTO.PlotDTO.PlotRequestDTO;
import com.huertos.comunidad_huertos_api.DTO.PlotDTO.PlotResponseDTO;
import com.huertos.comunidad_huertos_api.services.PlotService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    //extras
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

   /* @Operation(summary = "Get active plots filtered by soil type")
    @GetMapping("/garden/active/soilType")
    public ResponseEntity<List<PlotResponseDTO>> getActiveAndSoilType(
            @RequestParam("soilType") String soilType) {

        List<PlotResponseDTO> plots = service.findByActiveAndSoilType(soilType);
        return ResponseEntity.ok(plots);
    } */

    @Operation(summary = "Get active plots by soil type (path variable)")
    @GetMapping("/garden/active/soilType/{soilType}")
    public ResponseEntity<List<PlotResponseDTO>> getActiveAndSoilType(
            @PathVariable("soilType") String soilType) {

        List<PlotResponseDTO> plots = service.findByActiveAndSoilType(soilType);
        return ResponseEntity.ok(plots);
    }

}
