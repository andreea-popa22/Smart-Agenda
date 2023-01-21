package com.example.smartagenda.service;

import com.example.smartagenda.dto.CompanyDto;
import com.example.smartagenda.exception.ClientNotFoundException;
import com.example.smartagenda.exception.CompanyNotFoundException;
import com.example.smartagenda.exception.LocationNotFoundException;
import com.example.smartagenda.helper.Constants;
import com.example.smartagenda.mapper.CompanyMapper;
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
    private final CompanyMapper companyMapper;

    public CompanyService(CompanyRepository companyRepository, LocationRepository locationRepository, CompanyMapper companyMapper){
        this.companyRepository = companyRepository;
        this.locationRepository = locationRepository;
        this.companyMapper = companyMapper;
    }

    public Optional<Company> findCompanyById(int locationId) {
        return companyRepository.findById(locationId);
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

    public CompanyDto saveNewCompany(CompanyDto companyDto){
        Company company = companyMapper.fromCompanyDto(companyDto);
        Optional<Location> location = locationRepository.findLocationById(companyDto.getLocationId());
        company.setLocation(location.get());
        companyRepository.save(company);
        return companyMapper.toCompanyDto(company);
    }

    public boolean deleteCompany(String name) {
        Optional<Company> company = companyRepository.findCompanyByName(name);
        if (company.isEmpty()) {
            throw new CompanyNotFoundException(String.format(Constants.COMPANY_NOT_FOUND, name));
        }
        companyRepository.delete(company.get());
        return true;
    }
}
