package com.huertos.comunidad_huertos_api.mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.huertos.comunidad_huertos_api.model.DTO.UserDTO.UserRequestDTO;
import com.huertos.comunidad_huertos_api.model.DTO.UserDTO.UserResponseDTO;
import com.huertos.comunidad_huertos_api.model.entity.User;
import com.huertos.comunidad_huertos_api.model.entity.enums.UserAuthority;

public class UserMapper {

	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

	public static UserResponseDTO toDTO(User user) {
		UserResponseDTO dto = new UserResponseDTO();
		dto.setId(user.getId().toString());
		dto.setName(user.getName());
		dto.setEmail(user.getEmail());
		dto.setAuthority(user.getAuthority().name());
		dto.setJoinedAt(user.getJoinedAt().format(FORMATTER));
		return dto;
	}

	public static User toModel(UserRequestDTO userRequestDTO) {
		User user = new User();
		user.setName(userRequestDTO.getName());
		user.setEmail(userRequestDTO.getEmail());
		user.setAuthority(UserAuthority.valueOf(userRequestDTO.getAuthority().toUpperCase()));
		LocalDateTime joined = LocalDateTime.parse(userRequestDTO.getJoinedAt(), FORMATTER);
		user.setJoinedAt(joined);
		return user;
	}
}
