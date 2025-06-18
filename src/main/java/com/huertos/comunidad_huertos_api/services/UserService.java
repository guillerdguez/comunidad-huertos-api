package com.huertos.comunidad_huertos_api.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.huertos.comunidad_huertos_api.model.User;

public interface UserService {

	User save(User user);

	List<User> findAll();

	Optional<User> findById(UUID id);

	void deleteById(UUID id);
}
