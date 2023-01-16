package com.example.smartagenda.dto;
import jakarta.validation.constraints.*;
import java.time.LocalTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDto {
    @NotNull
    private ClientDto client;

    @NotNull
    private ProviderDto provider;

    @NotNull
    private LocationDto location;

    @NotNull
    private Date date;

    @NotNull
    private LocalTime startTime;

    @NotNull
    private LocalTime endTime;
}
