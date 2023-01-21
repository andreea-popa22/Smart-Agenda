package com.example.smartagenda.controller;

import com.example.smartagenda.dto.CompanyDto;
import com.example.smartagenda.exception.CompanyNotFoundException;
import com.example.smartagenda.helper.Constants;
import com.example.smartagenda.model.Company;
import com.example.smartagenda.repository.CompanyRepository;
import com.example.smartagenda.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("companies")
public class CompanyController {
    private final CompanyService companyService;
    private final CompanyRepository companyRepository;
    @Autowired
    public CompanyController(CompanyService companyService, CompanyRepository companyRepository) {
        this.companyService = companyService;
        this.companyRepository = companyRepository;
    }

    @GetMapping
    //@Operation(summary = "Getting all companys from database")
    public ResponseEntity<?> listCompanies() {
        return new ResponseEntity<>(companyService.retrieveAllCompanies(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCompany(@PathVariable int id) {
        Optional<Company> company = companyService.findCompanyById(id);
        if (company.isEmpty()) {
            throw new CompanyNotFoundException(Constants.ID_NOT_FOUND);
        }
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    @GetMapping("/location={locationId}")
    public ResponseEntity<?> findCompanyByType(@PathVariable int locationId){
        List<Company> companies = companyService.findCompaniesByLocation(locationId);
        return ResponseEntity.ok(companies);
    }

    @PostMapping("/add")
    public ResponseEntity<CompanyDto> addNewCompany(@Valid @RequestBody CompanyDto companyDto){
        return ResponseEntity.ok().body(companyService.saveNewCompany(companyDto));
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<String> deleteCompany(@PathVariable String name){
        companyService.deleteCompany(name);
        return ResponseEntity.ok(Constants.OBJECT_DELETED);
    }
}
