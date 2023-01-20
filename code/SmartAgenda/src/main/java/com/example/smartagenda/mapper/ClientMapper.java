package com.example.smartagenda.mapper;

import com.example.smartagenda.dto.ClientDto;
import com.example.smartagenda.enums.Gender;
import com.example.smartagenda.model.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {
    public Client fromClientDto(ClientDto clientDto) {
        return new Client(clientDto.getFirstName(),
                clientDto.getLastName(),
                clientDto.getPhoneNumber(),
                clientDto.getEmailAddress(),
                clientDto.getBirthdate(),
                clientDto.getGender());
    }

    public ClientDto toClientDto(Client client) {
        return new ClientDto(client.getFirstName(),
                client.getLastName(),
                client.getPhoneNumber(),
                client.getEmailAddress(),
                client.getBirthdate(),
                client.getGender().toString());
    }
}
