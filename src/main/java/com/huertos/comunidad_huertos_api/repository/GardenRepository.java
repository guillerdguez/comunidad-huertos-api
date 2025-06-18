package com.huertos.comunidad_huertos_api.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.huertos.comunidad_huertos_api.model.Garden;

@Repository
public interface GardenRepository extends CrudRepository<Garden, UUID> {

}
