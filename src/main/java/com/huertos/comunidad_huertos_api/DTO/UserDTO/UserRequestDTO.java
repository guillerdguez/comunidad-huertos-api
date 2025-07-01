package com.huertos.comunidad_huertos_api.DTO.UserDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

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

	public @NotBlank @Size(max = 10) String getName() {
		return name;
	}

	public void setName(@NotBlank @Size(max = 10) String name) {
		this.name = name;
	}

	public @NotBlank @Email String getEmail() {
		return email;
	}

	public void setEmail(@NotBlank @Email String email) {
		this.email = email;
	}

	public @NotBlank String getAuthority() {
		return authority;
	}

	public void setAuthority(@NotBlank String authority) {
		this.authority = authority;
	}

	public String getJoinedAt() {
		return joinedAt;
	}

	public void setJoinedAt(String joinedAt) {
		this.joinedAt = joinedAt;
	}
}
