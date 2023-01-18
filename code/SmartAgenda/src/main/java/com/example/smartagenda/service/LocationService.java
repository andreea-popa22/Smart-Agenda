package com.example.smartagenda.service;

import com.example.smartagenda.enums.LocationType;
import com.example.smartagenda.model.Appointment;
import com.example.smartagenda.model.Company;
import com.example.smartagenda.model.Location;
import com.example.smartagenda.repository.AppointmentRepository;
import com.example.smartagenda.repository.CompanyRepository;
import com.example.smartagenda.repository.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {
    private final LocationRepository locationRepository;
    private final CompanyRepository companyRepository;
    private final AppointmentRepository appointmentRepository;

    public LocationService(LocationRepository locationRepository, CompanyRepository companyRepository, AppointmentRepository appointmentRepository){
        this.locationRepository = locationRepository;
        this.companyRepository = companyRepository;
        this.appointmentRepository = appointmentRepository;
    }

    public List<Location> findLocationsByType(String type) {
        LocationType type1 = LocationType.valueOf(type);
        return locationRepository.findLocationsByType(type1);
    }

    public Location saveNewLocation(Location location){
        List<Company> companies = companyRepository.findCompaniesByLocation(location.getLocationId());
        List<Appointment> appointments = appointmentRepository.findAppointmentsByLocation(location.getLocationId());
        location.setCompanies(companies);
        location.setAppointments(appointments);
        return locationRepository.save(location);
    }
}
