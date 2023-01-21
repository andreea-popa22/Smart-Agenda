package com.example.smartagenda.repository;

import com.example.smartagenda.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    @Query("select a from Appointment a where a.appointmentId = :id")
    Optional<Appointment> findAppointmentById(int id);

    @Query("select a from Appointment a where a.client.clientId = :clientId")
    List<Appointment> findAppointmentsForClient(int clientId);

    @Query("select a from Appointment a where a.provider.providerId = :providerId")
    List<Appointment> findAppointmentsForProvider(int providerId);

    @Query("select a from Appointment a where a.location.locationId = :locationId")
    List<Appointment> findAppointmentsByLocation(int locationId);

}
