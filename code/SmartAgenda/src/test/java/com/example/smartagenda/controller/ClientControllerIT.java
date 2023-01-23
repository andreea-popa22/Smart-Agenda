package com.example.smartagenda.controller;

import com.example.smartagenda.dto.ClientDto;
import com.example.smartagenda.helper.Constants;
import com.example.smartagenda.mapper.ClientMapper;
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
        when(clientService.saveNewClient(Objects.clientDto1)).thenReturn(Objects.clientDto1);

        mockMvc.perform(post("/clients/add")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(Objects.clientDto1)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(objectMapper.writeValueAsString(Objects.clientDto1)));
    }

    @Test
    public void deleteClientTest() throws Exception {
        when(clientService.deleteClient(Objects.clientDto1.getFirstName(), Objects.clientDto1.getLastName())).thenReturn(true);

        mockMvc.perform(delete("/clients/delete/" + Objects.clientDto1.getFirstName() + "/" + Objects.clientDto1.getLastName()))
                .andExpect(status().isOk())
                .andExpect(content().string(Constants.OBJECT_DELETED));
    }

    @Test
    public void getAllClientsTest() throws Exception {
        when(clientService.retrieveAllClients()).thenReturn(Objects.clientDtos);

        mockMvc.perform(get("/clients"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(Objects.clientDtos)));
    }

    @Test
    public void updateClientTest() throws Exception {
        ClientDto updatedClient = Objects.clientDto1;
        updatedClient.setEmailAddress("alin_popescu22@gmail.com");

        when(clientService.editClient(Objects.clientDto1.getId(), updatedClient)).thenReturn(updatedClient);

        mockMvc.perform(put("/clients/edit/" + Objects.clientDto1.getId())
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(updatedClient)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(updatedClient)))
                .andExpect(jsonPath("$.emailAddress").value(Objects.clientDto1.getEmailAddress()))
                .andExpect(jsonPath("$.emailAddress").value(updatedClient.getEmailAddress()));
    }
}
