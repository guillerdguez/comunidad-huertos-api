package com.huertos.comunidad_huertos_api.services;

import com.huertos.comunidad_huertos_api.DTO.UserDTO.UserRequestDTO;
import com.huertos.comunidad_huertos_api.DTO.UserDTO.UserResponseDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {

//	User save(User user);
	UserResponseDTO createUser(UserRequestDTO user);

	UserResponseDTO updateUser(UUID id, UserRequestDTO user);

	List<UserResponseDTO> findAll();

	UserResponseDTO findById(UUID id);

	void deleteById(UUID id);
}
