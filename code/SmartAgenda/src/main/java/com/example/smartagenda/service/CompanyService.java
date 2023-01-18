package com.example.smartagenda.service;

import com.example.smartagenda.exception.ClientNotFoundException;
import com.example.smartagenda.exception.LocationNotFoundException;
import com.example.smartagenda.helper.Constants;
import com.example.smartagenda.model.Company;
import com.example.smartagenda.model.Location;
import com.example.smartagenda.repository.CompanyRepository;
import com.example.smartagenda.repository.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final LocationRepository locationRepository;

    public CompanyService(CompanyRepository companyRepository, LocationRepository locationRepository){
        this.companyRepository = companyRepository;
        this.locationRepository = locationRepository;
    }

    public List<Company> findCompaniesByLocation(int locationId) {
        return companyRepository.findCompaniesByLocation(locationId);
    }

    public Optional<Company> findCompanyByName(String name) {
        return companyRepository.findCompanyByName(name);
    }

    public List<Company> retrieveAllCompanies() {
        return companyRepository.findAll();
    }

    public Company saveNewCompany(Company company){
        Optional<Location> location = locationRepository.findById(company.getLocation().getLocationId());
        if (location.isEmpty()) {
            throw new LocationNotFoundException(String.format(Constants.LOCATION_NOT_FOUND, company.getLocation().getName()));
        }
        company.setLocation(location.get());
        return companyRepository.save(company);
    }
}
