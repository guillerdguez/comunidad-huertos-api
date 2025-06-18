package com.huertos.comunidad_huertos_api.model.DTO.UserDTO;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

public class CreateUserDTO {

	@NotBlank(message = "El nombre es obligatorio")
	private String name;

	@NotBlank(message = "El email es obligatorio")
	@Email(message = "El email debe tener un formato válido")
	private String email;

	@NotNull(message = "La autoridad es obligatoria")
	private String authority;
	@PastOrPresent(message = "La fecha de alta no puede estar en el futuro")
	private LocalDateTime joinedAt;

	public CreateUserDTO() {
	}

	public CreateUserDTO(String name, String email, String authority, LocalDateTime joinedAt) {
		this.name = name;
		this.email = email;
		this.authority = authority;
		this.joinedAt = joinedAt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public LocalDateTime getJoinedAt() {
		return joinedAt;
	}

	public void setJoinedAt(LocalDateTime joinedAt) {
		this.joinedAt = joinedAt;
	}
}
