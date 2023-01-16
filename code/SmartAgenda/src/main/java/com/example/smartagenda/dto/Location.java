package com.example.smartagenda.dto;

import com.example.smartagenda.enums.LocationType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Location {
    @NotBlank
    private String name;

    @NotBlank
    private String address;

    @NotNull
    private LocationType type;

    @NotNull
    private boolean isOffice = false;
}
