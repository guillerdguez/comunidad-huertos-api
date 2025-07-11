package com.huertos.comunidad_huertos_api.model.DTO.UserDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponseDTO {

	private String id;
	private String name;
	private String email;
	private String authority;
	private String joinedAt;

}
