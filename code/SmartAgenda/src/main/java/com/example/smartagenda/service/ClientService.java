package com.example.smartagenda.service;

import com.example.smartagenda.exception.ClientNotFoundException;
import com.example.smartagenda.helper.Constants;
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

    public Optional<Client> findClientByFullName(String firstName, String lastName) {
        return clientRepository.findClientByFullName(firstName, lastName);
    }

    public Optional<Client> findClientById(int clientId) {
        return clientRepository.findClientById(clientId);
    }

    public int totalClients() {
        return clientRepository.totalClients();
    }

    public Client editClient(String oldFirstName, String oldLastName, String newFirstName, String newLastName) {
        Optional<Client> client = clientRepository.findClientByFullName(oldFirstName, oldLastName);
        if (client.isEmpty()) {
            throw new ClientNotFoundException(String.format(Constants.CLIENT_NOT_FOUND, oldFirstName + ' ' + oldLastName));
        }
        Client updateClient = client.get();
        updateClient.setFirstName(newFirstName);
        updateClient.setLastName(newLastName);
        return clientRepository.save(updateClient);
    }

    public boolean deleteClient(String firstName, String lastName) {
        Optional<Client> client = clientRepository.findClientByFullName(firstName, lastName);
        if (client.isEmpty()) {
            throw new ClientNotFoundException(String.format(Constants.CLIENT_NOT_FOUND, firstName + ' ' + lastName));
        }
        clientRepository.delete(client.get());
        return true;
    }
}
