package com.example.smartagenda.controller;

import com.example.smartagenda.dto.LocationDto;
import com.example.smartagenda.exception.LocationNotFoundException;
import com.example.smartagenda.helper.Constants;
import com.example.smartagenda.model.Location;
import com.example.smartagenda.repository.LocationRepository;
import com.example.smartagenda.service.LocationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/locations")
public class LocationController {
    private final LocationService locationService;
    private final LocationRepository locationRepository;
    @Autowired
    public LocationController(LocationService locationService, LocationRepository locationRepository) {
        this.locationService = locationService;
        this.locationRepository = locationRepository;
    }

    @GetMapping
    //@Operation(summary = "Getting all locations from database")
    public ResponseEntity<?> listLocations() {
        return new ResponseEntity<>(locationService.retrieveAllLocations(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLocation(@PathVariable int id) {
        Optional<Location> location = locationService.findLocationById(id);
        if (location.isEmpty()) {
            throw new LocationNotFoundException(Constants.ID_NOT_FOUND);
        }
        return new ResponseEntity<>(location, HttpStatus.OK);
    }

    @GetMapping("/type={type}")
    public ResponseEntity<?> findLocationByType(@PathVariable String type){
        List<Location> locations = locationService.findLocationsByType(type);
        return ResponseEntity.ok(locations);
    }

    @PostMapping("/add")
    public ResponseEntity<LocationDto> addNewLocation(@Valid @RequestBody LocationDto locationDto){
        return ResponseEntity.ok().body(locationService.saveNewLocation(locationDto));
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<String> deleteLocation(@PathVariable String name){
        locationService.deleteLocation(name);
        return ResponseEntity.ok(Constants.OBJECT_DELETED);
    }
}
