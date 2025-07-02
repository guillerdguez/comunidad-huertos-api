package com.huertos.comunidad_huertos_api.services;

import com.huertos.comunidad_huertos_api.DTO.TaskDTO.TaskRequestDTO;
import com.huertos.comunidad_huertos_api.DTO.TaskDTO.TaskResponseDTO;

import java.util.List;
import java.util.UUID;


public interface TaskService {

    TaskResponseDTO createTask(TaskRequestDTO task);

    TaskResponseDTO updateTask(UUID id, TaskRequestDTO task);

    List<TaskResponseDTO> findAll();

    TaskResponseDTO findById(UUID id);

    void deleteById(UUID id);


}
