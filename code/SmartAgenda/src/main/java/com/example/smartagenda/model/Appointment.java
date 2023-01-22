package com.example.smartagenda.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int appointmentId;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private Provider provider;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @Column(name = "date")
    private Date date;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

//    public Appointment(int id, Client client, Provider provider, Location location, Date date, LocalTime startTime, LocalTime endTime) {
//        this.appointmentId = id;
//        this.client = client;
//        this.provider = provider;
//        this.location = location;
//        this.date = date;
//        this.startTime = startTime;
//        this.endTime = endTime;
//    }
}
