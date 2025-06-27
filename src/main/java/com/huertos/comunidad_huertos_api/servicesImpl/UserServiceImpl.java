package com.huertos.comunidad_huertos_api.servicesImpl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huertos.comunidad_huertos_api.model.User;
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

	@Override
	public User save(User garden) {
		log.debug("Guardando user: {}", garden);
		return repository.save(garden);
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> findAll() {
		log.debug("Recuperando todos los useres");
		return (List<User>) repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<User> findById(UUID id) {
		log.debug("Buscando user por ID: {}", id);
		return repository.findById(id);
	}

	@Override
	public void deleteById(UUID id) {
		log.debug("Eliminando user por ID: {}", id);
		repository.deleteById(id);
	}
}
