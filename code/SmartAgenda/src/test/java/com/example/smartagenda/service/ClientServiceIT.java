package com.example.smartagenda.service;

import com.example.smartagenda.dto.ClientDto;
import com.example.smartagenda.mapper.ClientMapper;
import com.example.smartagenda.model.Client;
import com.example.smartagenda.repository.ClientRepository;
import com.example.smartagenda.utils.Objects;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class ClientServiceIT {
    @InjectMocks
    ClientService clientService;

    @Mock
    ClientRepository clientRepository;

    @Mock
    ClientMapper clientMapper;

//    @Test
//    @DisplayName("Create client")
//    public void createClient() {
//        ClientDto clientDto = Objects.clientDto1;
//        Client client = Objects.client1;
//
//        when(clientMapper.fromClientDto(clientDto)).thenReturn(client);
//        when(clientMapper.toClientDto(client)).thenReturn(clientDto);
//        when(clientRepository.save(client)).thenReturn(client);
//
//        ClientDto result = clientService.saveNewClient(clientDto);
//
//        assertEquals(result.getFirstName(), clientDto.getFirstName());
//    }

    @Test
    @DisplayName("Get all clients")
    public void retrieveAllClients() {
        when(clientRepository.findAll()).thenReturn(Objects.clients);
        when(clientMapper.toClientDto(Objects.client1)).thenReturn(Objects.clientDto1);
        when(clientMapper.toClientDto(Objects.client2)).thenReturn(Objects.clientDto2);
        List<ClientDto> result = clientService.retrieveAllClients();
        assertEquals(result, Objects.clientDtos);
    }
}
