package com.example.smartagenda.service;

import com.example.smartagenda.dto.ClientDto;
import com.example.smartagenda.mapper.ClientMapper;
import com.example.smartagenda.model.Client;
import com.example.smartagenda.repository.AppointmentRepository;
import com.example.smartagenda.repository.ClientRepository;
import com.example.smartagenda.utils.Objects;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {
    @InjectMocks
    ClientService clientService;

    @Mock
    ClientRepository clientRepository;

    @Mock
    AppointmentRepository appointmentRepository;

    @Mock
    ClientMapper clientMapper;

    @Test
    public void getAllClientsTest() {
        Client client1 = Objects.client1;
        Client client2 = Objects.client2;
        ClientDto clientDto1 = Objects.clientDto1;
        ClientDto clientDto2 = Objects.clientDto2;
        List<Client> clientList = Objects.clients;
        List<ClientDto> clientDtos = Objects.clientDtos;

        when(clientRepository.findAll()).thenReturn(clientList);
        when(clientMapper.toClientDto(client1)).thenReturn(clientDto1);
        when(clientMapper.toClientDto(client2)).thenReturn(clientDto2);

        List<ClientDto> result = clientService.retrieveAllClients();
        assertEquals(result, clientDtos);
    }

    @Test
    public void testGetClientByName() {
        Client client1 = Objects.client1;
        ClientDto clientDto1 = Objects.clientDto1;

        when(clientRepository.findClientByFullName(Objects.client1.getFirstName(), Objects.client1.getLastName())).thenReturn(Optional.ofNullable(client1));
        when(clientMapper.toClientDto(client1)).thenReturn(clientDto1);

        Client result = clientService.findClientByFullName(Objects.clientDto1.getFirstName(), Objects.clientDto1.getLastName()).get();
        assertEquals(clientDto1, clientMapper.toClientDto(result));
        assertNotNull(result);
    }

    @Test
    public void testAddNewClient() {
        Client client = Objects.client1;
        ClientDto clientDto = Objects.clientDto1;

        when(clientMapper.fromClientDto(clientDto)).thenReturn(client);
        when(clientMapper.toClientDto(client)).thenReturn(clientDto);
        when(clientRepository.save(client)).thenReturn(client);

        ClientDto result = clientService.saveNewClient(clientDto);
        assertEquals(result, clientDto);
        assertThat(result.getFirstName()).isNotNull();
        assertThat(result.getLastName()).isNotNull();
        assertNotNull(result);
    }

    @Test
    public void testDeleteClient() {
        Client client = Objects.client1;
        ClientDto clientDto = Objects.clientDto1;

        when(clientRepository.findClientByFullName(Objects.clientDto1.getFirstName(), Objects.clientDto1.getLastName())).thenReturn(Optional.ofNullable(client));

        Boolean result = clientService.deleteClient(Objects.clientDto1.getFirstName(), Objects.clientDto1.getLastName());
        assertEquals(result, true);
    }

    @Test
    public void editClientTest() {
        Client client = Objects.client1;
        ClientDto clientDto = Objects.clientDto1;
        ClientDto clientDto1 = Objects.clientDto1;
        clientDto1.setEmailAddress("test@gmail.com");
        Client updatedClient = clientMapper.fromClientDto(clientDto1);

        when(clientRepository.findById(clientDto.getId())).thenReturn(Optional.ofNullable(client));
        //when(clientMapper.fromClientDto(clientDto1)).thenReturn(updatedClient);
        when(clientMapper.toClientDto(updatedClient)).thenReturn(clientDto1);
        when(clientRepository.save(updatedClient)).thenReturn(updatedClient);

        ClientDto result = clientService.editClient(Objects.clientDto1.getId(), clientDto1);
        assertEquals(result, clientDto1);
        assertEquals(result.getEmailAddress(), clientDto1.getEmailAddress());
        assertNotNull(result);
    }
}
