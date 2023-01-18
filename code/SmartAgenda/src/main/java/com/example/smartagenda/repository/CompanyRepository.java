package com.example.smartagenda.repository;

import com.example.smartagenda.model.Appointment;
import com.example.smartagenda.model.Company;
import com.example.smartagenda.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    @Query("select c from Company c where c.companyId = :locationId")
    List<Company> findCompaniesByLocation(int locationId);

    @Query("select c from Company c where c.name = :name")
    Optional<Company> findCompanyByName(String name);
}
