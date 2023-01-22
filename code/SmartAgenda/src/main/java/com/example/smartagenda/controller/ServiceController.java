package com.example.smartagenda.controller;

import com.example.smartagenda.dto.LocationDto;
import com.example.smartagenda.dto.ServiceDto;
import com.example.smartagenda.exception.ServiceNotFoundException;
import com.example.smartagenda.helper.Constants;
import com.example.smartagenda.mapper.ServiceMapper;
import com.example.smartagenda.model.Appointment;
import com.example.smartagenda.model.Company;
import com.example.smartagenda.model.Location;
import com.example.smartagenda.model.Service;
import com.example.smartagenda.repository.ServiceRepository;
import com.example.smartagenda.service.ServiceService;
import javax.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/services")
@Tag(name = "Services Controller", description = "Set of endpoints for managing services.")
public class ServiceController {
    private final ServiceService serviceService;
    private final ServiceRepository serviceRepository;
    private final ServiceMapper serviceMapper;
    @Autowired
    public ServiceController(ServiceService serviceService, ServiceRepository serviceRepository, ServiceMapper serviceMapper) {
        this.serviceService = serviceService;
        this.serviceRepository = serviceRepository;
        this.serviceMapper = serviceMapper;
    }

    @GetMapping
    @Operation(summary = "Getting all services from database.")
    public ResponseEntity<?> listServices() {
        return new ResponseEntity<>(serviceService.retrieveAllServices(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get service by id.")
    public ResponseEntity<?> getService(@PathVariable int id) {
        Optional<Service> service = serviceService.findServiceById(id);
        if (service.isEmpty()) {
            throw new ServiceNotFoundException(Constants.ID_NOT_FOUND);
        }
        return new ResponseEntity<>(service, HttpStatus.OK);
    }

    @GetMapping("/name={name}")
    @Operation(summary = "Find services by name.")
    public ResponseEntity<?> findServicesByName(@PathVariable String name){
        List<Service> services = serviceService.findServicesByName(name);
        return ResponseEntity.ok(services);
    }

    @PostMapping("/add")
    @Operation(summary = "Add new service.")
    public ResponseEntity<ServiceDto> addNewService(@Valid @RequestBody ServiceDto serviceDto){
        return ResponseEntity.ok(serviceService.saveNewService(serviceDto));
    }
}
