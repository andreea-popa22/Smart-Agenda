package com.example.smartagenda.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDto {
    private int id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;
}
