package com.example.smartagenda.model;

import com.example.smartagenda.helper.Hour;

import java.util.Date;

public class Appointment {
    private int appointmentId;
    private int clientId;
    private int providerId;
    private int locationId;
    private Date date;
    private Hour startTime;
    private Hour endTime;
}
