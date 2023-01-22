package com.example.smartagenda.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "companies")
public class Company {
    @Id
    @Column(name = "company_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int companyId;

    @Column(name = "name")
    private String name;

    @Column(name = "contact")
    private String contact;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @JsonIgnore
    @OneToMany(mappedBy = "company")
    private List<Provider> providers;

    public Company(int id, String name, String contact, Location location) {
        this.companyId = id;
        this.name = name;
        this.contact = contact;
        this.location = location;
    }
}
