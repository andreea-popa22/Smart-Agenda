package com.example.smartagenda.controller;

import com.example.smartagenda.dto.ClientDto;
import com.example.smartagenda.exception.ClientAlreadyExistsException;
import com.example.smartagenda.exception.ClientNotFoundException;
import com.example.smartagenda.helper.Constants;
import com.example.smartagenda.model.Client;
import com.example.smartagenda.repository.ClientRepository;
import com.example.smartagenda.service.ClientService;
import javax.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/clients")
@Tag(name = "Clients Controller", description = "Set of endpoints for managing clients.")
public class ClientController {

    private final ClientService clientService;
    private final ClientRepository clientRepository;
    @Autowired
    public ClientController(ClientService clientService, ClientRepository clientRepository) {
        this.clientService = clientService;
        this.clientRepository = clientRepository;
    }

    @GetMapping
    @Operation(summary = "Retrieving all clients from database.")
    public ResponseEntity<?> listClients() {
        return new ResponseEntity<>(clientService.retrieveAllClients(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get client by id.")
    public ResponseEntity<?> getClient(@PathVariable int id) {
        Optional<Client> client = clientService.findClientById(id);
        if (client.isEmpty()) {
            throw new ClientNotFoundException(Constants.ID_NOT_FOUND);
        }
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @GetMapping("/{firstName}/{lastName}")
    @Operation(summary = "Find client by full name (firstname, lastname).")
    public ResponseEntity<Client> findClientByFullName(@PathVariable String firstName, @PathVariable String lastName){
        Optional<Client> client = clientService.findClientByFullName(firstName, lastName);
        if (client.isEmpty()) {
            throw new ClientNotFoundException(Constants.ID_NOT_FOUND);
        }
        return ResponseEntity.ok(client.get());
    }

    @DeleteMapping("/delete/{firstName}/{lastName}")
    @Operation(summary = "Delete client by full name (firstname, lastname).")
    public ResponseEntity<String> deleteClient(@PathVariable String firstName, @PathVariable String lastName){
        clientService.deleteClient(firstName, lastName);
        return ResponseEntity.ok(Constants.OBJECT_DELETED);
    }

    @PostMapping("/add")
    @Operation(summary = "Add a new client.")
    public ResponseEntity<ClientDto> addNewClient(@Valid @RequestBody ClientDto clientDto){
        return ResponseEntity.ok().body(clientService.saveNewClient(clientDto));
    }

    @PutMapping("/edit/{id}")
    @Operation(summary = "Edit client details (except the id).")
    public ResponseEntity<?> editClient(@PathVariable int id, @RequestBody ClientDto editedClientDto) {
        return ResponseEntity.ok(clientService.editClient(id, editedClientDto));
    }
}
