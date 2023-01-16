package com.example.smartagenda.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Service {
    @NotBlank
    private String name;

    @NotBlank
    private String description;
}
