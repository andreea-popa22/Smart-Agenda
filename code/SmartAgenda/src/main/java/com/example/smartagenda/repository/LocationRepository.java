package com.example.smartagenda.repository;

import com.example.smartagenda.enums.LocationType;
import com.example.smartagenda.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Integer> {
    @Query("select l from Location l where l.type = :type")
    List<Location> findLocationsByType(LocationType type);

    @Query("select l from Location l where l.locationId = :locationId")
    Location findLocationById(int locationId);
}
