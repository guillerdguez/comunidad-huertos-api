package com.huertos.comunidad_huertos_api.servicesImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huertos.comunidad_huertos_api.exception.EmailAlreadyExistsException;
import com.huertos.comunidad_huertos_api.exception.UserNotFoundException;
import com.huertos.comunidad_huertos_api.mapper.UserMapper;
import com.huertos.comunidad_huertos_api.model.DTO.UserDTO.UserRequestDTO;
import com.huertos.comunidad_huertos_api.model.DTO.UserDTO.UserResponseDTO;
import com.huertos.comunidad_huertos_api.model.entity.User;
import com.huertos.comunidad_huertos_api.model.entity.enums.UserAuthority;
import com.huertos.comunidad_huertos_api.repository.UserRepository;
import com.huertos.comunidad_huertos_api.services.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	private final UserRepository repository;

	public UserServiceImpl(UserRepository repository) {
		this.repository = repository;
	}

	// @Override
//	public User save(User garden) {
//		log.debug("Guardando user: {}", garden);
//		return repository.save(garden);
//	}
	@Override
	public UserResponseDTO createUser(UserRequestDTO user) {
		if (repository.existsByEmail(user.getEmail())) {
			throw new EmailAlreadyExistsException("A patient with this email " + "already exists" + user.getEmail());
		}
		log.debug("Guardando user: {}", user);

		User newUser = repository.save(UserMapper.toModel(user));
		return UserMapper.toDTO(newUser);
	}

	@Override
	public UserResponseDTO updateUser(UUID id, UserRequestDTO userRequest) {
		User user = repository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
		if (repository.existsByEmailAndIdNot(userRequest.getEmail(), id)) {
			throw new EmailAlreadyExistsException(
					"A user with this email " + "already exists" + userRequest.getEmail());
		}
		user.setName(userRequest.getName());
		user.setEmail(userRequest.getEmail());
		user.setAuthority(UserAuthority.valueOf(userRequest.getAuthority()));
		user.setJoinedAt(
				LocalDateTime.parse(userRequest.getJoinedAt(), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));

		User updatedUser = repository.save(user);

		return UserMapper.toDTO(updatedUser);
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserResponseDTO> findAll() {
		log.debug("Recuperando todos los users");

		List<User> users = repository.findAll();

		return users.stream().map(UserMapper::toDTO).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public UserResponseDTO findById(UUID id) {
		User user = repository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
		return UserMapper.toDTO(user);
	}

	@Override
	public void deleteById(UUID id) {
		log.debug("Eliminando user por ID: {}", id);
		repository.deleteById(id);
	}

	// extra
	@Override
	@Transactional(readOnly = true)
	public List<UserResponseDTO> findDistinctByPlotsActiveFalse() {
		List<User> users = repository.findDistinctByPlotsActiveFalse();

		return users.stream().map(UserMapper::toDTO).collect(Collectors.toList());
	}
}
