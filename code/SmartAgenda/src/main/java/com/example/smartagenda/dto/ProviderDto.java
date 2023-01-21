package com.example.smartagenda.dto;

import com.example.smartagenda.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProviderDto {
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

    @NotNull
    private int companyId;
}
