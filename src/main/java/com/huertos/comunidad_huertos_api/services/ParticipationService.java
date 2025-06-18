package com.huertos.comunidad_huertos_api.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.huertos.comunidad_huertos_api.model.Participation;

public interface ParticipationService {
	Participation save(Participation participation);

	List<Participation> findAll();

	Optional<Participation> findById(UUID id);

	void deleteById(UUID id);
}
