package com.example.smartagenda.controller;

import com.example.smartagenda.model.Client;
import com.example.smartagenda.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class ClientController {
    private final ClientService clientService;


    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/")
    public String listClient(Model model) {
        model.addAttribute("client", new Client());
        model.addAttribute("data", clientService.retrieveAllClients());
        return "index";
    }

    @GetMapping("/delete/{firstName}")
    public String deleteClient(Model model, @PathVariable String firstName) {
        model.addAttribute("client", new Client());
        clientService.deleteClient(firstName);
        model.addAttribute("data", clientService.retrieveAllClients());
        return "index";
    }

    @PostMapping("/client/add")
    public String addClient(@ModelAttribute("client") Client client, Model model) {
        clientService.addClient(client);
        model.addAttribute("data", clientService.retrieveAllClients());
        return "index";
    }
}
