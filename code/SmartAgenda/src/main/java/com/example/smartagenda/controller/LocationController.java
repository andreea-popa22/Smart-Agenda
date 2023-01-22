package com.example.smartagenda.controller;

import com.example.smartagenda.dto.LocationDto;
import com.example.smartagenda.exception.LocationNotFoundException;
import com.example.smartagenda.helper.Constants;
import com.example.smartagenda.model.Location;
import com.example.smartagenda.repository.LocationRepository;
import com.example.smartagenda.service.LocationService;
import javax.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/locations")
@Tag(name = "Locations Controller", description = "Set of endpoints for managing locations.")
public class LocationController {
    private final LocationService locationService;
    private final LocationRepository locationRepository;
    @Autowired
    public LocationController(LocationService locationService, LocationRepository locationRepository) {
        this.locationService = locationService;
        this.locationRepository = locationRepository;
    }

    @GetMapping
    @Operation(summary = "Retrieve all locations from database.")
    public ResponseEntity<?> listLocations() {
        return new ResponseEntity<>(locationService.retrieveAllLocations(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get location by id.")
    public ResponseEntity<?> getLocation(@PathVariable int id) {
        Optional<Location> location = locationService.findLocationById(id);
        if (location.isEmpty()) {
            throw new LocationNotFoundException(Constants.ID_NOT_FOUND);
        }
        return new ResponseEntity<>(location, HttpStatus.OK);
    }

    @GetMapping("/type={type}")
    @Operation(summary = "Find locations by type (salon, studio, etc.).")
    public ResponseEntity<?> findLocationByType(@PathVariable String type){
        List<Location> locations = locationService.findLocationsByType(type);
        return ResponseEntity.ok(locations);
    }

    @PostMapping("/add")
    @Operation(summary = "Add new location.")
    public ResponseEntity<LocationDto> addNewLocation(@Valid @RequestBody LocationDto locationDto){
        return ResponseEntity.ok().body(locationService.saveNewLocation(locationDto));
    }

    @DeleteMapping("/delete/{name}")
    @Operation(summary = "Delete location by id.")
    public ResponseEntity<String> deleteLocation(@PathVariable String name){
        locationService.deleteLocation(name);
        return ResponseEntity.ok(Constants.OBJECT_DELETED);
    }
}
