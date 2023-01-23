package com.example.smartagenda.controller;

import com.example.smartagenda.dto.ClientDto;
import com.example.smartagenda.helper.Constants;
import com.example.smartagenda.model.Client;
import com.example.smartagenda.service.ClientService;
import com.example.smartagenda.utils.Objects;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClientControllerTest {
    @InjectMocks
    ClientController clientController;

    @Mock
    ClientService clientService;

    @Test
    public void getAllClientsTest() {
        List<ClientDto> clientDtos = Objects.clientDtos;

        when(clientService.retrieveAllClients()).thenReturn(clientDtos);

        ResponseEntity<List<ClientDto>> result = clientController.listClients();
        assertEquals(result.getBody(), clientDtos);
        assertEquals(result.getStatusCode().value(), 200);
    }

    @Test
    public void getClientByNameTest() {
        ClientDto clientDto = Objects.clientDto1;
        Client client = Objects.client1;

        when(clientService.findClientByFullName(Objects.clientDto1.getFirstName(), Objects.clientDto1.getLastName())).thenReturn(Optional.ofNullable(client));

        ResponseEntity<Client> result = clientController.findClientByFullName(Objects.clientDto1.getFirstName(), Objects.clientDto1.getLastName());
        assertEquals(result.getBody(), client);
        assertEquals(result.getStatusCode().value(), 200);
    }

    @Test
    public void addNewClientTest() {
        ClientDto clientDto = Objects.clientDto1;

        when(clientService.saveNewClient(clientDto)).thenReturn(clientDto);

        ResponseEntity<ClientDto> result = clientController.addNewClient(clientDto);
        assertEquals(result.getBody(), clientDto);
        assertEquals(result.getStatusCode().value(), 200);
    }

    @Test
    public void deleteClientTest() {
        when( clientService.deleteClient(Objects.clientDto1.getFirstName(), Objects.clientDto1.getLastName())).thenReturn(true);

        ResponseEntity<String> result = clientController.deleteClient(Objects.clientDto1.getFirstName(), Objects.clientDto1.getLastName());
        assertEquals(result.getBody(), Constants.OBJECT_DELETED);
        assertEquals(result.getStatusCode().value(), 200);
    }

    @Test
    public void updateClientTest() {
        ClientDto clientDto = Objects.clientDto1;
        ClientDto clientDto2 = Objects.clientDto1;
        clientDto2.setPhoneNumber("0760000000");

        when(clientService.editClient(Objects.clientDto1.getId(), clientDto2)).thenReturn(clientDto2);

        ResponseEntity<ClientDto> result = clientController.editClient(Objects.clientDto1.getId(), clientDto2);
        assertEquals(result.getBody(), clientDto);
        assertEquals(java.util.Objects.requireNonNull(result.getBody()).getPhoneNumber(), clientDto.getPhoneNumber());
        assertEquals(result.getStatusCode().value(), 200);
    }
}
