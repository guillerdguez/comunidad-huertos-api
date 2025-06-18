package com.huertos.comunidad_huertos_api.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.huertos.comunidad_huertos_api.model.Event;

@Repository
public interface EventRepository extends CrudRepository<Event, UUID> {

}
