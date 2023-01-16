package com.example.smartagenda.dto;

import com.example.smartagenda.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class Provider {
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String phone;

    @NotBlank
    private String email;

    @NotNull
    private Date birthdate;

    @NotNull
    private Gender gender;

    @NotNull
    private Company company;
}
