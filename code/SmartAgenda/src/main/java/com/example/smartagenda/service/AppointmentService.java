package com.example.smartagenda.service;

import com.example.smartagenda.dto.AppointmentDto;
import com.example.smartagenda.exception.EntityNotFoundException;
import com.example.smartagenda.helper.Constants;
import com.example.smartagenda.mapper.AppointmentMapper;
import com.example.smartagenda.model.Appointment;
import com.example.smartagenda.model.Client;
import com.example.smartagenda.model.Location;
import com.example.smartagenda.model.Provider;
import com.example.smartagenda.repository.AppointmentRepository;
import com.example.smartagenda.repository.ClientRepository;
import com.example.smartagenda.repository.LocationRepository;
import com.example.smartagenda.repository.ProviderRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final ClientRepository clientRepository;
    private final ProviderRepository providerRepository;
    private final LocationRepository locationRepository;
    private final AppointmentMapper appointmentMapper;

    public AppointmentService(AppointmentRepository appointmentRepository, AppointmentMapper appointmentMapper,
    ClientRepository clientRepository, ProviderRepository providerRepository, LocationRepository locationRepository) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentMapper = appointmentMapper;
        this.clientRepository = clientRepository;
        this.providerRepository = providerRepository;
        this.locationRepository = locationRepository;
    }

    public Optional<Appointment> findAppointmentById(int id) {
        return appointmentRepository.findAppointmentById(id);
    }

    public List<Appointment> findAppointmentsForClient(int clientId) {
        return appointmentRepository.findAppointmentsForClient(clientId);
    }

    public List<Appointment> findAppointmentsForProvider(int providerId) {
        return appointmentRepository.findAppointmentsForProvider(providerId);
    }

    public List<Appointment> findAppointmentsByLocation(int locationId) {
        return appointmentRepository.findAppointmentsByLocation(locationId);
    }

    public List<Appointment> filterAppointmentsForClientByDate(int clientId, Date date){
        List<Appointment> clientAppointments = findAppointmentsForClient(clientId);
        return clientAppointments
                .stream()
                .filter(a -> a.getDate() == date)
                .sorted(Comparator.comparing(Appointment::getDate))
                .collect(Collectors.toList());
    }

    public AppointmentDto saveNewAppointment(AppointmentDto appointmentDto){
        Appointment appointment = appointmentMapper.fromAppointmentDto(appointmentDto);
        Optional<Client> client = clientRepository.findClientById(appointmentDto.getClientId());
        Optional<Provider> provider = providerRepository.findProviderById(appointmentDto.getProviderId());
        Optional<Location> location = locationRepository.findLocationById(appointmentDto.getLocationId());
        if (client.isEmpty()) {
            throw new EntityNotFoundException(Constants.ID_NOT_FOUND);
        }
        if (provider.isEmpty()) {
            throw new EntityNotFoundException(Constants.ID_NOT_FOUND);
        }
        if (location.isEmpty()) {
            throw new EntityNotFoundException(Constants.ID_NOT_FOUND);
        }
        appointment.setClient(client.get());
        appointment.setProvider(provider.get());
        appointment.setLocation(location.get());
        appointmentRepository.save(appointment);
        return appointmentMapper.toAppointmentDto(appointment);
    }

    public AppointmentDto editAppointment(int id, String date) throws ParseException {
        Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        appointment.get().setDate(date1);
        appointmentRepository.save(appointment.get());
        return appointmentMapper.toAppointmentDto(appointment.get());
    }

    public boolean deleteAppointment(int id) {
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(() -> new RuntimeException(Constants.ID_NOT_FOUND));
        appointmentRepository.deleteById(id);
        return true;
    }
}
