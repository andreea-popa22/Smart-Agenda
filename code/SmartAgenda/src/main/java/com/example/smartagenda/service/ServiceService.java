package com.example.smartagenda.service;

import com.example.smartagenda.exception.ServiceNotFoundException;
import com.example.smartagenda.helper.Constants;
import com.example.smartagenda.repository.ServiceRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ServiceService {
    private final ServiceRepository serviceRepository;

    public ServiceService(ServiceRepository serviceRepository){
        this.serviceRepository = serviceRepository;
    }

    public com.example.smartagenda.model.Service getServiceByName(String name){
        Optional<com.example.smartagenda.model.Service> service = serviceRepository.findByName(name);
        if (service.isEmpty()) {
            throw new ServiceNotFoundException(String.format(Constants.SERVICE_NOT_FOUND, name));
        }
        return service.get();
    }
}
