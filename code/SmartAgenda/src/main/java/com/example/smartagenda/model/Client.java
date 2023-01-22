package com.example.smartagenda.model;

import com.example.smartagenda.enums.Gender;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clients")
public class Client {
    @Id
    @Column(name = "client_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int clientId;

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

    @OneToMany(mappedBy = "client")
    private List<Appointment> appointments;

    public Client(int id, String firstName, String lastName, String phoneNumber, String emailAddress, Date birthdate, String gender) {
        this.clientId = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.birthdate = birthdate;
        this.gender = gender;
    }
}
