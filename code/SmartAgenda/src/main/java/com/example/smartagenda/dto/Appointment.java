package com.example.smartagenda.dto;
import jakarta.validation.constraints.*;

import java.time.LocalTime;
import java.util.Date;


public class Appointment {
    @NotNull
    private Client client;

    @NotNull
    private Provider provider;

    @NotNull
    private Location location;

    @NotNull
    private Date date;

    @NotNull
    private LocalTime startTime;

    @NotNull
    private LocalTime endTime;
}
