package com.example.smartagenda.model;

import com.example.smartagenda.enums.Gender;

import java.util.Date;

public class Client {
    private int clientId;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private Date birthdate;
    private Gender gender;

    public Client() {
    }

    public Client(String firstName, String lastName, String phone, String email, Date birthdate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.birthdate = birthdate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }
}
