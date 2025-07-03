package com.huertos.comunidad_huertos_api.repository;

import com.huertos.comunidad_huertos_api.model.Plot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PlotRepository extends JpaRepository<Plot, UUID> {
    List<Plot> findByGardenId(UUID gardenId);

    List<Plot> findByOwnerIsNull();

    List<Plot> findByGardenIdAndOwnerIsNull(UUID gardenId);

    List<Plot> findByActiveTrueAndSoilType(String soilType);

}
