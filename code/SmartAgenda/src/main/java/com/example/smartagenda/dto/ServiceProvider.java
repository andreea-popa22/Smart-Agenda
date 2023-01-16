package com.example.smartagenda.dto;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class ServiceProvider {
    @NotNull
    private float price;

    @NotNull
    private float duration;

    @NotBlank
    private String description;
}
