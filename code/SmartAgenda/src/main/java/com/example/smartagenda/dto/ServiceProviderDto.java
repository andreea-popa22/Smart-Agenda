package com.example.smartagenda.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceProviderDto {
    private int id;

    @NotNull
    private float price;

    @NotNull
    private float duration;

    @NotBlank
    private String description;
}
