package com.example.smartagenda.dto;

import com.example.smartagenda.enums.LocationType;
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
public class LocationDto {
    private int id;

    @NotBlank
    private String name;

    @NotBlank
    private String address;

    @NotNull
    private String type;

    @NotNull
    private boolean isOffice = false;
}
