package com.example.smartagenda.service;

import com.example.smartagenda.model.Appointment;
import com.example.smartagenda.model.Client;
import com.example.smartagenda.repository.AppointmentRepository;
import com.example.smartagenda.repository.ClientRepository;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final AppointmentRepository appointmentRepository;

    public ClientService(ClientRepository clientRepository, AppointmentRepository appointmentRepository) {
        this.clientRepository = clientRepository;
        this.appointmentRepository = appointmentRepository;
    }

    public List<Client> retrieveAllClients() {
        return clientRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Client::getFirstName))
                .collect(Collectors.toList());
    }

    public Client saveNewClient(Client client){
        List<Appointment> appointments = appointmentRepository.findAppointmentsForClient(client.getClientId());
        client.setAppointments(appointments);
        return clientRepository.save(client);
    }

    public Optional<Client> findClientByFullName(Client client) {
        return clientRepository.findClientByFullName(client.getFirstName(), client.getLastName());
    }

    public int totalClients() {
        return clientRepository.totalClients();
    }
}
