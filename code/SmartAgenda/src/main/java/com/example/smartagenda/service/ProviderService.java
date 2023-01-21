package com.example.smartagenda.service;

import com.example.smartagenda.dto.ClientDto;
import com.example.smartagenda.dto.ProviderDto;
import com.example.smartagenda.exception.ClientNotFoundException;
import com.example.smartagenda.exception.ProviderNotFoundException;
import com.example.smartagenda.helper.Constants;
import com.example.smartagenda.mapper.ProviderMapper;
import com.example.smartagenda.model.Appointment;
import com.example.smartagenda.model.Client;
import com.example.smartagenda.model.Provider;
import com.example.smartagenda.repository.AppointmentRepository;
import com.example.smartagenda.repository.ProviderRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProviderService {
    private final ProviderRepository providerRepository;
    private final AppointmentRepository appointmentRepository;
    private final ProviderMapper providerMapper;

    public ProviderService(ProviderRepository providerRepository, AppointmentRepository appointmentRepository, ProviderMapper providerMapper) {
        this.providerRepository = providerRepository;
        this.appointmentRepository = appointmentRepository;
        this.providerMapper = providerMapper;
    }

    public List<Provider> retrieveAllProviders() {
        return providerRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Provider::getFirstName))
                .collect(Collectors.toList());
    }

    public Optional<Provider> findProviderById(int id) {
        return providerRepository.findById(id);
    }

    public ProviderDto saveNewProvider(ProviderDto providerDto){
        Provider provider = providerMapper.fromProviderDto(providerDto);
        List<Appointment> appointments = appointmentRepository.findAppointmentsForProvider(provider.getProviderId());
        provider.setAppointments(appointments);
        providerRepository.save(provider);
        return providerMapper.toProviderDto(provider);
    }

    public Optional<Provider> findProviderByFullName(String firstName, String lastName) {
        return providerRepository.findProviderByFullName(firstName, lastName);
    }

    public int totalProviders() {
        return providerRepository.totalProviders();
    }

    public boolean deleteProvider(String firstName, String lastName) {
        Optional<Provider> provider = providerRepository.findProviderByFullName(firstName, lastName);
        if (provider.isEmpty()) {
            throw new ProviderNotFoundException(String.format(Constants.CLIENT_NOT_FOUND, firstName + ' ' + lastName));
        }
        providerRepository.delete(provider.get());
        return true;
    }

    public ProviderDto editProvider(int id, ProviderDto editedProviderDto) {
        Optional<Provider> provider = providerRepository.findProviderById(id);
        if (provider.isEmpty()) {
            throw new ClientNotFoundException(Constants.ID_NOT_FOUND);
        }

        Provider updateProvider = provider.get();
        updateProvider.setFirstName(editedProviderDto.getFirstName());
        updateProvider.setLastName(editedProviderDto.getLastName());
        updateProvider.setPhoneNumber(editedProviderDto.getPhoneNumber());
        updateProvider.setBirthdate(editedProviderDto.getBirthdate());
        updateProvider.setGender(editedProviderDto.getGender());
        updateProvider.setEmailAddress(editedProviderDto.getEmailAddress());

        return providerMapper.toProviderDto(providerRepository.save(updateProvider));
    }
}
