package com.example.smartagenda.mapper;

import com.example.smartagenda.dto.ServiceDto;
import com.example.smartagenda.model.Service;
import org.springframework.stereotype.Component;

@Component
public class ServiceMapper {
    public Service fromServiceDto(ServiceDto serviceDto){
        return new Service(serviceDto.getId(),
                serviceDto.getName(),
                serviceDto.getDescription());
    }

    public ServiceDto toServiceDto(Service service){
        return new ServiceDto(service.getServiceId(),
                service.getName(),
                service.getDescription());
    }
}
