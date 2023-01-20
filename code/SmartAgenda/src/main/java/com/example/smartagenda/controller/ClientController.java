package com.example.smartagenda.controller;

import com.example.smartagenda.exception.ClientAlreadyExistsException;
import com.example.smartagenda.exception.ClientNotFoundException;
import com.example.smartagenda.helper.Constants;
import com.example.smartagenda.model.Client;
import com.example.smartagenda.repository.ClientRepository;
import com.example.smartagenda.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;
    private final ClientRepository clientRepository;
    @Autowired
    public ClientController(ClientService clientService, ClientRepository clientRepository) {
        this.clientService = clientService;
        this.clientRepository = clientRepository;
    }

    @GetMapping
    //@Operation(summary = "Getting all clients from database")
    public ResponseEntity<?> listClient(Model model) {
        return new ResponseEntity<>(clientService.retrieveAllClients(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClient(@PathVariable int id) {
        Optional<Client> client = clientService.findClientById(id);
        if (client.isEmpty()) {
            throw new ClientNotFoundException(Constants.ID_NOT_FOUND);
        }
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @GetMapping("/{firstName}/{lastName}")
    public ResponseEntity<Client> findClientByFullName(@PathVariable String firstName, @PathVariable String lastName){
        Optional<Client> client = clientService.findClientByFullName(firstName, lastName);
        if (client.isEmpty()) {
            throw new ClientNotFoundException(Constants.ID_NOT_FOUND);
        }
        return ResponseEntity.ok(client.get());
    }

    @GetMapping("/delete/{firstName}/{lastName}")
    public ResponseEntity<String> deleteClient(@PathVariable String firstName, @PathVariable String lastName){
        clientService.deleteClient(firstName, lastName);
        return ResponseEntity.ok(Constants.OBJECT_DELETED);
    }

    @PostMapping("/client/add")
    public String addClient(@ModelAttribute("client") Client client, Model model) {
        clientService.saveNewClient(client);
        model.addAttribute("data", clientService.retrieveAllClients());
        return "index";
    }

    @PostMapping("/add")
    public ResponseEntity<Client> addNewClient(@Valid @RequestBody Client client){
        return ResponseEntity.ok(clientService.saveNewClient(client));
    }

    @PutMapping("/{oldFirstName}/{oldLastName}/{newFirstName}/{newLastName}")
    public ResponseEntity<?> editClient(@PathVariable String oldFirstName, @PathVariable String oldLastName, @PathVariable String newFirstName, @PathVariable String newLastName) {
        return ResponseEntity.ok(clientService.editClient(oldFirstName, oldLastName, newFirstName, newLastName));
    }
}
