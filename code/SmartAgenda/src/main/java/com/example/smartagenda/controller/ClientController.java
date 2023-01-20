package com.example.smartagenda.controller;

import com.example.smartagenda.model.Client;
import com.example.smartagenda.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;
    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<?> listClient(Model model) {
        return new ResponseEntity<>(clientService.retrieveAllClients(), HttpStatus.OK);
    }

//    @GetMapping("/delete/{firstName}")
//    public String deleteClient(Model model, @PathVariable String firstName) {
//        model.addAttribute("client", new Client());
//        clientService.deleteClient(firstName);
//        model.addAttribute("data", clientService.retrieveAllClients());
//        return "index";
//    }

    @PostMapping("/client/add")
    public String addClient(@ModelAttribute("client") Client client, Model model) {
        clientService.saveNewClient(client);
        model.addAttribute("data", clientService.retrieveAllClients());
        return "index";
    }
}
