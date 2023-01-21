package com.example.smartagenda.service;

import com.example.smartagenda.dto.LocationDto;
import com.example.smartagenda.enums.LocationType;
import com.example.smartagenda.exception.ClientNotFoundException;
import com.example.smartagenda.exception.LocationNotFoundException;
import com.example.smartagenda.helper.Constants;
import com.example.smartagenda.mapper.LocationMapper;
import com.example.smartagenda.model.Appointment;
import com.example.smartagenda.model.Client;
import com.example.smartagenda.model.Company;
import com.example.smartagenda.model.Location;
import com.example.smartagenda.repository.AppointmentRepository;
import com.example.smartagenda.repository.CompanyRepository;
import com.example.smartagenda.repository.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {
    private final LocationRepository locationRepository;
    private final CompanyRepository companyRepository;
    private final AppointmentRepository appointmentRepository;
    private final LocationMapper locationMapper;

    public LocationService(LocationRepository locationRepository, CompanyRepository companyRepository, AppointmentRepository appointmentRepository, LocationMapper locationMapper){
        this.locationRepository = locationRepository;
        this.companyRepository = companyRepository;
        this.appointmentRepository = appointmentRepository;
        this.locationMapper = locationMapper;
    }

    public List<Location> retrieveAllLocations() {
        return locationRepository.findAll();
    }

    public Optional<Location> findLocationById(int id) {
        return locationRepository.findLocationById(id);
    }

    public List<Location> findLocationsByType(String type) {
        LocationType type1 = LocationType.valueOf(type);
        return locationRepository.findLocationsByType(type1);
    }

    public LocationDto saveNewLocation(LocationDto locationDto){
        Location location = locationMapper.fromLocationDto(locationDto);
        List<Company> companies = companyRepository.findCompaniesByLocation(locationDto.getId());
        List<Appointment> appointments = appointmentRepository.findAppointmentsByLocation(locationDto.getId());
        location.setCompanies(companies);
        location.setAppointments(appointments);
        locationRepository.save(location);
        return locationMapper.toLocationDto(location);
    }

    public boolean deleteLocation(String name) {
        Optional<Location> location = locationRepository.findLocationByName(name);
        if (location.isEmpty()) {
            throw new LocationNotFoundException(String.format(Constants.LOCATION_NOT_FOUND, name));
        }
        locationRepository.delete(location.get());
        return true;
    }
}
