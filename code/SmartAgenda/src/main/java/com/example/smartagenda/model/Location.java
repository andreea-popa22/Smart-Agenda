package com.example.smartagenda.model;

import com.example.smartagenda.enums.LocationType;
import jakarta.persistence.*;
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
@Table(name = "locations")
public class Location {
    @Id
    @Column(name = "location_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int locationId;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "location_type")
    @Enumerated(EnumType.STRING)
    private LocationType type;

    @Column(name = "is_office")
    private boolean isOffice = false;

    @OneToMany(mappedBy = "companies")
    private List<Company> companies;

    @OneToMany(mappedBy = "appointments")
    private List<Appointment> appointments;
}
