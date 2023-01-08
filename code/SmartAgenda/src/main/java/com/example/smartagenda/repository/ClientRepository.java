package com.example.smartagenda.repository;

import com.example.smartagenda.model.Client;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.smartagenda.helper.MethodsHelper.parseDate;

@Repository
public class ClientRepository {
    private static List<Client> clientList = new ArrayList<>();

    public ClientRepository() {
        Client p1 = new Client("p1 first name", "p1 last name", "0765434567", "test", parseDate("2014-02-14"));
        Client p2 = new Client("p2 first name", "p2 last name", "0765676667", "test", parseDate("2014-02-14"));
        Client p3 = new Client("p3 first name", "p3 last name", "0765670067", "test",parseDate("2014-02-14"));

        clientList.add(p1);
        clientList.add(p2);
        clientList.add(p3);
    }

    public List<Client> retrieveAllClients() {
        return clientList;
    }

    public String addClient(Client client) {
        clientList.add(client);
        return "A new person has been added with success!";
    }

    public String deleteClient(String firstName) {
        clientList = clientList.stream()
                .filter(client -> !client.getFirstName().equals(firstName))
                .collect(Collectors.toList());
        return "Person with name" + firstName + "has been deleted";
    }

}
