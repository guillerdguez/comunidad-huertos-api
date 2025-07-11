package com.huertos.comunidad_huertos_api.servicesImpl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huertos.comunidad_huertos_api.exception.EventNotFoundException;
import com.huertos.comunidad_huertos_api.exception.ParticipationNotFoundException;
import com.huertos.comunidad_huertos_api.exception.UserNotFoundException;
import com.huertos.comunidad_huertos_api.mapper.ParticipationMapper;
import com.huertos.comunidad_huertos_api.model.DTO.ParticipationDTO.ParticipationRequestDTO;
import com.huertos.comunidad_huertos_api.model.DTO.ParticipationDTO.ParticipationResponseDTO;
import com.huertos.comunidad_huertos_api.model.entity.Event;
import com.huertos.comunidad_huertos_api.model.entity.Participation;
import com.huertos.comunidad_huertos_api.model.entity.User;
import com.huertos.comunidad_huertos_api.repository.EventRepository;
import com.huertos.comunidad_huertos_api.repository.ParticipationRepository;
import com.huertos.comunidad_huertos_api.repository.UserRepository;
import com.huertos.comunidad_huertos_api.services.ParticipationService;

@Service
@Transactional
public class ParticipationServiceImpl implements ParticipationService {

	private static final Logger log = LoggerFactory.getLogger(ParticipationServiceImpl.class);
	private final ParticipationRepository repository;
	private final EventRepository eventRepository;
	private final UserRepository userRepository;

	public ParticipationServiceImpl(ParticipationRepository repository, EventRepository eventRepository,
			UserRepository userRepository) {
		this.repository = repository;
		this.eventRepository = eventRepository;
		this.userRepository = userRepository;
	}

	@Override
	public ParticipationResponseDTO createParticipation(ParticipationRequestDTO participation) {

		log.debug("Guardando participation: {}", participation);

		Participation newParticipation = repository.save(ParticipationMapper.toModel(participation));
		return ParticipationMapper.toDTO(newParticipation);
	}

	@Override
	public ParticipationResponseDTO updateParticipation(UUID id, ParticipationRequestDTO participationRequest) {
		Participation participation = repository.findById(id)
				.orElseThrow(() -> new ParticipationNotFoundException("Participation not found with ID: " + id));

		participation.setStatus(participationRequest.getStatus());
		Event event = eventRepository.findById(participationRequest.getEventId()).orElseThrow(
				() -> new EventNotFoundException("Event not found with ID: " + participationRequest.getEventId()));
		participation.setEvent(event);

		User user = userRepository.findById(participationRequest.getUserId()).orElseThrow(
				() -> new UserNotFoundException("User not found with ID: " + participationRequest.getUserId()));
		participation.setUser(user);

		Participation updatedParticipation = repository.save(participation);

		return ParticipationMapper.toDTO(updatedParticipation);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ParticipationResponseDTO> findAll() {
		log.debug("Recuperando todos los participations");

		List<Participation> participations = (List<Participation>) repository.findAll();

		return participations.stream().map(ParticipationMapper::toDTO).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public ParticipationResponseDTO findById(UUID id) {
		Participation participation = repository.findById(id)
				.orElseThrow(() -> new ParticipationNotFoundException("Participation not found with ID: " + id));
		return ParticipationMapper.toDTO(participation);
	}

	@Override
	public void deleteById(UUID id) {
		log.debug("Eliminando participation por ID: {}", id);
		repository.deleteById(id);
	}
}
