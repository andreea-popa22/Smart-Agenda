package com.example.smartagenda.mapper;

import com.example.smartagenda.dto.AppointmentDto;
import com.example.smartagenda.model.Appointment;
import com.example.smartagenda.model.Client;
import com.example.smartagenda.model.Location;
import com.example.smartagenda.model.Provider;
import com.example.smartagenda.repository.ClientRepository;
import com.example.smartagenda.repository.LocationRepository;
import com.example.smartagenda.repository.ProviderRepository;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {
    private ClientRepository clientRepository;
    private LocationRepository locationRepository;
    private ProviderRepository providerRepository;

    public Appointment fromAppointmentDto(AppointmentDto appointmentDto) {
        Client client = clientRepository.findClientById(appointmentDto.getClientId());
        Provider provider = providerRepository.findProviderById(appointmentDto.getProviderId());
        Location location = locationRepository.findLocationById(appointmentDto.getLocationId());

        return new Appointment(client,
                provider,
                location,
                appointmentDto.getDate(),
                appointmentDto.getStartTime(),
                appointmentDto.getEndTime());
    }

    public AppointmentDto toAppointmentDto(Appointment appointment) {
        return new AppointmentDto(appointment.getClient().getClientId(),
                appointment.getProvider().getProviderId(),
                appointment.getLocation().getLocationId(),
                appointment.getDate(),
                appointment.getStartTime(),
                appointment.getEndTime());
    }
}
