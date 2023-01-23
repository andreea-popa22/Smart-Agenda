package com.example.smartagenda.utils;

import com.example.smartagenda.dto.ClientDto;
import com.example.smartagenda.model.Client;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class Objects {
    public static ClientDto clientDto1 = new ClientDto("Alin", "Popescu", "0765432345", "alin_popescu@gmail.com", new Date(), "M");
    public static ClientDto clientDto2 = new ClientDto("Alina", "Popescu", "0765432345", "alina_popescu@gmail.com", new Date(), "F");
    public static List<ClientDto> clientDtos = List.of(new ClientDto[]{clientDto1, clientDto2});

    public static Client client1 = new Client(8, "Alin", "Popescu", "0765432345", "alin_popescu@gmail.com", new Date(), "M");
    public static Client client2 = new Client("Alina", "Popescu", "0765432345", "alina_popescu@gmail.com", new Date(), "F");
    public static List<Client> clients = List.of(new Client[]{client1, client2});


}
