package com.example.smartagenda.repository;

import com.example.smartagenda.enums.LocationType;
import com.example.smartagenda.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Integer> {
    @Query("select l from Location l where l.type = :type")
    List<Location> findLocationsByType(LocationType type);

    @Query("select l from Location l where l.locationId = :locationId")
    Optional<Location> findLocationById(int locationId);

    @Query("select l from Location l where l.name = :name")
    Optional<Location> findLocationByName(String name);
}
