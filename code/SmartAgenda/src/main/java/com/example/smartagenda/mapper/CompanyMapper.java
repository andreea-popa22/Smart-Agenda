package com.example.smartagenda.mapper;

import com.example.smartagenda.dto.CompanyDto;
import com.example.smartagenda.model.Company;
import com.example.smartagenda.model.Location;
import com.example.smartagenda.repository.LocationRepository;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {
    private LocationRepository locationRepository;

    public Company fromCompanyDto(CompanyDto companyDto){
        Location location = locationRepository.findLocationById(companyDto.getLocationId());
        return new Company(companyDto.getName(),
                companyDto.getContact(),
                location);
    }

    public CompanyDto toCompanyDto(Company company){
        return new CompanyDto(company.getName(),
                company.getContact(),
                company.getLocation().getLocationId());
    }
}
