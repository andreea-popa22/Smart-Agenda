package com.example.smartagenda.service;

import com.example.smartagenda.dto.ServiceDto;
import com.example.smartagenda.exception.ServiceNotFoundException;
import com.example.smartagenda.helper.Constants;
import com.example.smartagenda.mapper.ServiceMapper;
import com.example.smartagenda.repository.ServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceService {
    private final ServiceRepository serviceRepository;
    private final ServiceMapper serviceMapper;

    public ServiceService(ServiceRepository serviceRepository, ServiceMapper serviceMapper){
        this.serviceRepository = serviceRepository;
        this.serviceMapper = serviceMapper;
    }

    public List<com.example.smartagenda.model.Service> findServicesByName(String name){
        List<com.example.smartagenda.model.Service> services = serviceRepository.findServicesByName(name);
        return services;
    }

    public List<com.example.smartagenda.model.Service> retrieveAllServices() {
        return serviceRepository.findAll();
    }

    public Optional<com.example.smartagenda.model.Service> findServiceById(int id) {
        return serviceRepository.findServiceById(id);
    }

    public ServiceDto saveNewService(ServiceDto serviceDto){
        com.example.smartagenda.model.Service service = serviceMapper.fromServiceDto(serviceDto);
        serviceRepository.save(service);
        return serviceMapper.toServiceDto(service);
    }
}
