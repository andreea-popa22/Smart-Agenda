package com.example.smartagenda.dto;

import com.example.smartagenda.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class Client {
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String emailAddress;

    @NotNull
    private Date birthdate;

    @NotNull
    private Gender gender;
}
