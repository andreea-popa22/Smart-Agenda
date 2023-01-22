package com.example.smartagenda.dto;
import javax.validation.constraints.*;
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
    private int id;

    @NotNull
    private int clientId;

    @NotNull
    private int providerId;

    @NotNull
    private int locationId;

    @NotNull
    private Date date;

    @NotNull
    private LocalTime startTime;

    @NotNull
    private LocalTime endTime;
}
