package com.huertos.comunidad_huertos_api.controller;

import com.huertos.comunidad_huertos_api.DTO.UserDTO.UserRequestDTO;
import com.huertos.comunidad_huertos_api.DTO.UserDTO.UserResponseDTO;
import com.huertos.comunidad_huertos_api.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Create a new User")
    public ResponseEntity<UserResponseDTO> create(@RequestBody UserRequestDTO user) {

        UserResponseDTO userResponseDTO = service.createUser(user);

        return ResponseEntity.ok().body(userResponseDTO);
    }

    @GetMapping
    @Operation(summary = "Get all Users")
    public ResponseEntity<List<UserResponseDTO>> getAll() {
        List<UserResponseDTO> users = service.findAll();

        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get User by ID")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable UUID id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a new User")
    public ResponseEntity<UserResponseDTO> update(@PathVariable UUID id,
                                                  @Valid @RequestBody UserRequestDTO userRequest) {

        UserResponseDTO userResponseDTO = service.updateUser(id, userRequest);
        return ResponseEntity.ok().body(userResponseDTO);

    }

    //no puedes borrar si tiene eventos relacionados
    @DeleteMapping("/{id}")

    @Operation(summary = "Delete a User")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();

//		Optional<User> userOpt = service.findById(id);
//		if (userOpt.isPresent()) {
//			service.deleteById(id);
//			return ResponseEntity.noContent().build();
//			return ResponseEntity.noContent().build();
//		} else {
//			return ResponseEntity.notFound().build();
//		}
    }

    @GetMapping("/with-inactive-plots")
    @Operation(summary = "Get all User With Plots Not Active ")
    public ResponseEntity<List<UserResponseDTO>> getUserWithPlotsNotActive() {
        List<UserResponseDTO> users = service.findDistinctByPlotsActiveFalse();

        return ResponseEntity.ok().body(users);
    }
}
