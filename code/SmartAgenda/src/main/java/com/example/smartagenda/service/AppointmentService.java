package com.example.smartagenda.service;

import com.example.smartagenda.model.Appointment;
import com.example.smartagenda.model.Client;
import com.example.smartagenda.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
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
                .sorted(Comparator.comparing(Appointment::getDate))
                .collect(Collectors.toList());
    }
}
