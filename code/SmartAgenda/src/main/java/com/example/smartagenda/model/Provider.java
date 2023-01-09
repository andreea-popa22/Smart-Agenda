package com.example.smartagenda.model;

import com.example.smartagenda.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "providers")
public class Provider {
    @Id
    @Column(name = "provider_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int providerId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "birthdate")
    private Date birthdate;

    @Column(name = "gender")
    private Gender gender;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
}
