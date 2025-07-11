package com.huertos.comunidad_huertos_api.services;

import java.util.List;
import java.util.UUID;

import com.huertos.comunidad_huertos_api.model.DTO.UserDTO.UserRequestDTO;
import com.huertos.comunidad_huertos_api.model.DTO.UserDTO.UserResponseDTO;

public interface UserService {

	// User save(User user);
	UserResponseDTO createUser(UserRequestDTO user);

	UserResponseDTO updateUser(UUID id, UserRequestDTO user);

	List<UserResponseDTO> findAll();

	UserResponseDTO findById(UUID id);

	void deleteById(UUID id);

	// extras
	List<UserResponseDTO> findDistinctByPlotsActiveFalse();

}
