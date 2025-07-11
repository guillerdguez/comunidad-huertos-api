package com.huertos.comunidad_huertos_api.model.DTO.UserDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRequestDTO {

	@NotBlank(message = "Name is required")
	@Size(max = 10, message = "Name cannot exceed 10 characters")
	private String name;

	@NotBlank(message = "Email is required")
	@Email(message = "Email should be valid")
	private String email;

	@NotBlank(message = "Authority is required")
	private String authority;

//	@NotBlank(groups = CreateUserValidationGroup.class, message = "Joined date is required")
	private String joinedAt;

}
