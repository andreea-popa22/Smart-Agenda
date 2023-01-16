package com.example.smartagenda.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {
    @NotBlank
    private String name;

    @NotBlank
    private String contact;

    @NotNull
    private int locationId;
}
