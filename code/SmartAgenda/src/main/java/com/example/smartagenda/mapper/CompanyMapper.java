package com.example.smartagenda.mapper;

import com.example.smartagenda.dto.CompanyDto;
import com.example.smartagenda.model.Company;
import com.example.smartagenda.model.Location;
import com.example.smartagenda.repository.LocationRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CompanyMapper {
    private LocationRepository locationRepository;

    public CompanyMapper(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Company fromCompanyDto(CompanyDto companyDto){
        Optional<Location> location = locationRepository.findLocationById(companyDto.getLocationId());
        return new Company(companyDto.getId(),
                companyDto.getName(),
                companyDto.getContact(),
                location.get());
    }

    public CompanyDto toCompanyDto(Company company){
        return new CompanyDto(company.getCompanyId(),
                company.getName(),
                company.getContact(),
                company.getLocation().getLocationId());
    }
}
