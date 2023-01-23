package com.example.smartagenda.controller;

import com.example.smartagenda.dto.ClientDto;
import com.example.smartagenda.helper.Constants;
import com.example.smartagenda.mapper.ClientMapper;
import com.example.smartagenda.model.Client;
import com.example.smartagenda.repository.AppointmentRepository;
import com.example.smartagenda.repository.ClientRepository;
import com.example.smartagenda.service.ClientService;
import com.example.smartagenda.utils.Objects;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ClientController.class)
public class ClientControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ClientRepository clientRepository;

    @MockBean
    private AppointmentRepository appointmentRepository;

    @MockBean
    private ClientMapper clientMapper;

    @MockBean
    private ClientService clientService;

    @Test
    public void createClientTest() throws Exception {
        when(clientService.saveNewClient(Objects.client)).thenReturn(Objects.client);

        mockMvc.perform(post("/clients/add")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(Objects.client)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(objectMapper.writeValueAsString(Objects.client)));
    }

    @Test
    public void deleteClientTest() throws Exception {
        when(clientService.deleteClient(Objects.client.getFirstName(), Objects.client.getLastName())).thenReturn(true);

        mockMvc.perform(delete("/clients/delete/" + Objects.client.getFirstName() + "/" + Objects.client.getLastName()))
                .andExpect(status().isOk())
                .andExpect(content().string(Constants.OBJECT_DELETED));
    }

    @Test
    public void getAllClientsTest() throws Exception {
        when(clientService.retrieveAllClients()).thenReturn(Objects.clients);

        mockMvc.perform(get("/clients"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(Objects.clients)));
    }

    @Test
    public void updateClientTest() throws Exception {
        ClientDto updatedClient = Objects.client;
        updatedClient.setEmailAddress("alin_popescu22@gmail.com");

        when(clientService.editClient(Objects.client.getId(), updatedClient)).thenReturn(updatedClient);

        mockMvc.perform(put("/clients/edit/" + Objects.client.getId())
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(updatedClient)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(updatedClient)))
                .andExpect(jsonPath("$.emailAddress").value(Objects.client.getEmailAddress()))
                .andExpect(jsonPath("$.emailAddress").value(updatedClient.getEmailAddress()));
    }
}
