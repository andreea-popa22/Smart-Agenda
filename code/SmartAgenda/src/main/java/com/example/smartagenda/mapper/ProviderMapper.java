package com.example.smartagenda.mapper;

import com.example.smartagenda.dto.ProviderDto;
import com.example.smartagenda.enums.Gender;
import com.example.smartagenda.model.Company;
import com.example.smartagenda.model.Provider;
import com.example.smartagenda.repository.CompanyRepository;
import org.springframework.stereotype.Component;

@Component
public class ProviderMapper {
    private CompanyRepository companyRepository;

    public Provider fromProviderDto(ProviderDto providerDto) {
        Company company = companyRepository.getCompanyById(providerDto.getCompanyId());
        return new Provider(providerDto.getFirstName(),
                providerDto.getLastName(),
                providerDto.getPhoneNumber(),
                providerDto.getEmailAddress(),
                providerDto.getBirthdate(),
                Gender.valueOf(providerDto.getGender()),
                company);
    }

    public ProviderDto toProviderDto(Provider provider) {
        return new ProviderDto(provider.getFirstName(),
                provider.getLastName(),
                provider.getPhoneNumber(),
                provider.getEmailAddress(),
                provider.getBirthdate(),
                provider.getGender().toString(),
                provider.getCompany().getCompanyId());
    }
}
