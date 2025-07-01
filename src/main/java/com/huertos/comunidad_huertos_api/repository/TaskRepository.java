package com.huertos.comunidad_huertos_api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.huertos.comunidad_huertos_api.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {

}
