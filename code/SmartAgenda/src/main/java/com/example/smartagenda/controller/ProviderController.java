package com.example.smartagenda.controller;

import com.example.smartagenda.dto.ProviderDto;
import com.example.smartagenda.exception.ProviderNotFoundException;
import com.example.smartagenda.helper.Constants;
import com.example.smartagenda.model.Provider;
import com.example.smartagenda.repository.ProviderRepository;
import com.example.smartagenda.service.ProviderService;
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
@RequestMapping("/providers")
@Tag(name = "Providers Controller", description = "Set of endpoints for managing providers.")
public class ProviderController {
    private final ProviderService providerService;
    private final ProviderRepository providerRepository;
    @Autowired
    public ProviderController(ProviderService providerService, ProviderRepository providerRepository) {
        this.providerService = providerService;
        this.providerRepository = providerRepository;
    }

    @GetMapping
    @Operation(summary = "Getting all providers from database.")
    public ResponseEntity<?> listProviders(Model model) {
        return new ResponseEntity<>(providerService.retrieveAllProviders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get provider by id.")
    public ResponseEntity<?> getProvider(@PathVariable int id) {
        Optional<Provider> provider = providerService.findProviderById(id);
        if (provider.isEmpty()) {
            throw new ProviderNotFoundException(Constants.ID_NOT_FOUND);
        }
        return new ResponseEntity<>(provider, HttpStatus.OK);
    }

    @GetMapping("/{firstName}/{lastName}")
    @Operation(summary = "Get provider by full name (firstname, lastname).")
    public ResponseEntity<Provider> findProviderByFullName(@PathVariable String firstName, @PathVariable String lastName){
        Optional<Provider> provider = providerService.findProviderByFullName(firstName, lastName);
        if (provider.isEmpty()) {
            throw new ProviderNotFoundException(Constants.ID_NOT_FOUND);
        }
        return ResponseEntity.ok(provider.get());
    }

    @DeleteMapping("/delete/{firstName}/{lastName}")
    @Operation(summary = "Delete provider by full name(firstname, lastname).")
    public ResponseEntity<String> deleteProvider(@PathVariable String firstName, @PathVariable String lastName){
        providerService.deleteProvider(firstName, lastName);
        return ResponseEntity.ok(Constants.OBJECT_DELETED);
    }

    @PostMapping("/add")
    @Operation(summary = "Add new provider.")
    public ResponseEntity<ProviderDto> addNewProvider(@Valid @RequestBody ProviderDto providerDto){
        return ResponseEntity.ok(providerService.saveNewProvider(providerDto));
    }

    @PutMapping("/edit/{id}")
    @Operation(summary = "Edit provider details (except for id).")
    public ResponseEntity<?> editProvider(@PathVariable int id, @RequestBody ProviderDto editedProviderDto) {
        return ResponseEntity.ok(providerService.editProvider(id, editedProviderDto));
    }
}
