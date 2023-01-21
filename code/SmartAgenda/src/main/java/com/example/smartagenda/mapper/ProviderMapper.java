package com.example.smartagenda.mapper;

import com.example.smartagenda.dto.ProviderDto;
import com.example.smartagenda.enums.Gender;
import com.example.smartagenda.exception.CompanyNotFoundException;
import com.example.smartagenda.helper.Constants;
import com.example.smartagenda.model.Company;
import com.example.smartagenda.model.Provider;
import com.example.smartagenda.repository.CompanyRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProviderMapper {
    private CompanyRepository companyRepository;

    public ProviderMapper(CompanyRepository companyRepository){
        this.companyRepository = companyRepository;
    }

    public Provider fromProviderDto(ProviderDto providerDto) {
        Optional<Company> company = companyRepository.findById(providerDto.getCompanyId());
        if (company.isEmpty()) {
            throw new CompanyNotFoundException(Constants.ID_NOT_FOUND);
        }
        return new Provider(providerDto.getId(),
                providerDto.getFirstName(),
                providerDto.getLastName(),
                providerDto.getPhoneNumber(),
                providerDto.getEmailAddress(),
                providerDto.getBirthdate(),
                providerDto.getGender(),
                company.get());
    }

    public ProviderDto toProviderDto(Provider provider) {
        return new ProviderDto(provider.getProviderId(),
                provider.getFirstName(),
                provider.getLastName(),
                provider.getPhoneNumber(),
                provider.getEmailAddress(),
                provider.getBirthdate(),
                provider.getGender().toString(),
                provider.getCompany().getCompanyId());
    }
}
