package com.huertos.comunidad_huertos_api.servicesImpl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.huertos.comunidad_huertos_api.model.User;
import com.huertos.comunidad_huertos_api.services.UserService;

public class UserServiceImpl implements UserService {

	@Override
	public User save(User user) {
		return null;
	}

	@Override
	public List<User> findAll() {
		return null;
	}

	@Override
	public Optional<User> findById(UUID id) {
		return Optional.empty();
	}

	@Override
	public void deleteById(UUID id) {
	}

}
