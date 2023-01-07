package com.example.smartagenda.model;

import com.example.smartagenda.enums.Gender;

import java.util.Date;

public class Client {
    //protected int personId;
    protected String firstName;
    protected String lastName;
    protected String phone;
    protected String email;
    protected Date birthdate;
    protected Gender gender;

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
