package com.huertos.comunidad_huertos_api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.huertos.comunidad_huertos_api.DTO.UserDTO.UserRequestDTO;
import com.huertos.comunidad_huertos_api.DTO.UserDTO.UserResponseDTO;
import com.huertos.comunidad_huertos_api.exception.EmailAlreadyExistsException;
import com.huertos.comunidad_huertos_api.exception.UserNotFoundException;
import com.huertos.comunidad_huertos_api.model.entity.User;
import com.huertos.comunidad_huertos_api.model.enums.UserAuthority;
import com.huertos.comunidad_huertos_api.repository.UserRepository;
import com.huertos.comunidad_huertos_api.servicesImpl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

	@Mock
	private UserRepository repository;

	@InjectMocks
	private UserServiceImpl service;

	private UserRequestDTO requestDto;
	private User entity;

	@BeforeEach
	void init() {
		requestDto = new UserRequestDTO();
		requestDto.setName("John Doe");
		requestDto.setEmail("john@example.com");
		requestDto.setAuthority("MEMBER"); // literal real

		entity = new User();
		entity.setId(UUID.randomUUID());
		entity.setName(requestDto.getName());
		entity.setEmail(requestDto.getEmail());
		entity.setAuthority(UserAuthority.MEMBER); // ← nunca nulo
		entity.setJoinedAt(
				LocalDateTime.parse("01/07/2025 12:00:00", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
	}

	@Test
	void createUser_returnsDto_whenEmailNotExists() {
		when(repository.existsByEmail(requestDto.getEmail())).thenReturn(false);
		when(repository.save(any(User.class))).thenReturn(entity);

		UserResponseDTO result = service.createUser(requestDto);

		assertNotNull(result);
		assertEquals(entity.getEmail(), result.getEmail());
		verify(repository).save(any(User.class));
	}

	@Test
	void createUser_throwsException_whenEmailExists() {
		when(repository.existsByEmail(requestDto.getEmail())).thenReturn(true);
		assertThrows(EmailAlreadyExistsException.class, () -> service.createUser(requestDto));
		verify(repository, never()).save(any(User.class));
	}

	@Test
	void updateUser_returnsDto_whenUserExistsAndEmailFree() {
		UUID id = entity.getId();
		when(repository.findById(id)).thenReturn(Optional.of(entity));
		when(repository.existsByEmailAndIdNot(requestDto.getEmail(), id)).thenReturn(false);
		when(repository.save(any(User.class))).thenReturn(entity);

		UserResponseDTO updated = service.updateUser(id, requestDto);

		assertEquals(entity.getName(), updated.getName());
		verify(repository).save(entity);
	}

	@Test
	void updateUser_throwsException_whenUserNotFound() {
		UUID id = UUID.randomUUID();
		when(repository.findById(id)).thenReturn(Optional.empty());
		assertThrows(UserNotFoundException.class, () -> service.updateUser(id, requestDto));
	}

	@Test
	void updateUser_throwsException_whenEmailDuplicate() {
		UUID id = entity.getId();
		when(repository.findById(id)).thenReturn(Optional.of(entity));
		when(repository.existsByEmailAndIdNot(requestDto.getEmail(), id)).thenReturn(true);
		assertThrows(EmailAlreadyExistsException.class, () -> service.updateUser(id, requestDto));
	}

	@Test
	void findAll_returnsList() {
		when(repository.findAll()).thenReturn(List.of(entity));

		List<UserResponseDTO> result = service.findAll();

		assertEquals(1, result.size());
		verify(repository).findAll();
	}

	@Test
	void findById_returnsDto_whenExists() {
		UUID id = entity.getId();
		when(repository.findById(id)).thenReturn(Optional.of(entity));

		UserResponseDTO result = service.findById(id);

		assertEquals(id, result.getId());
		verify(repository).findById(id);
	}

	@Test
	void findById_throwsException_whenNotFound() {
		UUID id = UUID.randomUUID();
		when(repository.findById(id)).thenReturn(Optional.empty());
		assertThrows(UserNotFoundException.class, () -> service.findById(id));
	}

	@Test
	void deleteById_invokesRepository() {
		UUID id = UUID.randomUUID();
		service.deleteById(id);
		verify(repository).deleteById(id);
	}
}
