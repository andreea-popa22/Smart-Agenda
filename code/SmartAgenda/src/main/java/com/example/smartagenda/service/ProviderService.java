package com.example.smartagenda.service;

import com.example.smartagenda.model.Appointment;
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

    public ProviderService(ProviderRepository providerRepository, AppointmentRepository appointmentRepository) {
        this.providerRepository = providerRepository;
        this.appointmentRepository = appointmentRepository;
    }

    public List<Provider> retrieveAllProviders() {
        return providerRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Provider::getFirstName))
                .collect(Collectors.toList());
    }

    public Provider saveNewProvider(Provider provider){
        List<Appointment> appointments = appointmentRepository.findAppointmentsForProvider(provider.getProviderId());
        provider.setAppointments(appointments);
        return providerRepository.save(provider);
    }

    public Optional<Provider> findProviderByFullName(Provider provider) {
        return providerRepository.findProviderByFullName(provider.getFirstName(), provider.getLastName());
    }

    public int totalProviders() {
        return providerRepository.totalProviders();
    }
}
