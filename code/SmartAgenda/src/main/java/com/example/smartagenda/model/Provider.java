package com.example.smartagenda.model;

import com.example.smartagenda.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

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

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "birthdate")
    private Date birthdate;

    @Column(name = "gender")
    //@Enumerated(EnumType.STRING)
    private String gender;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "provider")
    private List<Appointment> appointments;

    public Provider(String firstName, String lastName, String phoneNumber, String emailAddress, Date birthdate, String gender, Company company) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.birthdate = birthdate;
        this.gender = gender;
        this.company = company;
    }
}
