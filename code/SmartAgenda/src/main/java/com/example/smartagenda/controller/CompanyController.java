package com.example.smartagenda.controller;

import com.example.smartagenda.dto.CompanyDto;
import com.example.smartagenda.exception.CompanyNotFoundException;
import com.example.smartagenda.helper.Constants;
import com.example.smartagenda.model.Company;
import com.example.smartagenda.repository.CompanyRepository;
import com.example.smartagenda.service.CompanyService;
import javax.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("companies")
@Tag(name = "Companies Controller", description = "Set of endpoints for managing companies.")
public class CompanyController {
    private final CompanyService companyService;
    private final CompanyRepository companyRepository;
    @Autowired
    public CompanyController(CompanyService companyService, CompanyRepository companyRepository) {
        this.companyService = companyService;
        this.companyRepository = companyRepository;
    }

    @GetMapping
    @Operation(summary = "Retrieve all companies from database.")
    public ResponseEntity<?> listCompanies() {
        return new ResponseEntity<>(companyService.retrieveAllCompanies(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get company by id.")
    public ResponseEntity<?> getCompany(@PathVariable int id) {
        Optional<Company> company = companyService.findCompanyById(id);
        if (company.isEmpty()) {
            throw new CompanyNotFoundException(Constants.ID_NOT_FOUND);
        }
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    @GetMapping("/location={locationId}")
    @Operation(summary = "Find companies by location.")
    public ResponseEntity<?> findCompaniesByType(@PathVariable int locationId){
        List<Company> companies = companyService.findCompaniesByLocation(locationId);
        return ResponseEntity.ok(companies);
    }

    @PostMapping("/add")
    @Operation(summary = "Add a new company.")
    public ResponseEntity<CompanyDto> addNewCompany(@Valid @RequestBody CompanyDto companyDto){
        return ResponseEntity.ok().body(companyService.saveNewCompany(companyDto));
    }

    @DeleteMapping("/delete/{name}")
    @Operation(summary = "Delete company by id.")
    public ResponseEntity<String> deleteCompany(@PathVariable String name){
        companyService.deleteCompany(name);
        return ResponseEntity.ok(Constants.OBJECT_DELETED);
    }
}
