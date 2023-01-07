package com.example.smartagenda.service;

import com.example.smartagenda.model.Client;
import com.example.smartagenda.repository.ClientRepository;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> retrieveAllClients() {
        return clientRepository.retrieveAllClients()
                .stream()
                .sorted(Comparator.comparing(Client::getFirstName))
                .collect(Collectors.toList());
    }

    public String addClient(Client client) {
        return clientRepository.addClient(client);
    }

    public String deleteClient(String firstName) {
        return clientRepository.deleteClient(firstName);
    }
}
