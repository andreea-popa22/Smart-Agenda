package com.example.smartagenda.dto;

import com.example.smartagenda.model.Location;
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
public class CompanyDto {
    private int id;

    @NotBlank
    private String name;

    @NotBlank
    private String contact;

    @NotNull
    private int locationId;
}
