package com.example.smartagenda.dto;

import com.example.smartagenda.enums.Gender;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
    private int id;

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
    private String gender;

    public ClientDto(String firstName, String lastName, String phoneNumber, String emailAddress, Date birthdate, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.birthdate = birthdate;
        this.gender = gender;
    }
}
