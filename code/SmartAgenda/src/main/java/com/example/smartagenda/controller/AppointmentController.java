package com.example.smartagenda.controller;

import com.example.smartagenda.dto.AppointmentDto;
import com.example.smartagenda.exception.AppointmentNotFoundException;
import com.example.smartagenda.helper.Constants;
import com.example.smartagenda.model.Appointment;
import com.example.smartagenda.repository.AppointmentRepository;
import com.example.smartagenda.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;
    private final AppointmentRepository appointmentRepository;
    @Autowired
    public AppointmentController(AppointmentService appointmentService, AppointmentRepository appointmentRepository) {
        this.appointmentService = appointmentService;
        this.appointmentRepository = appointmentRepository;
    }

    @GetMapping("/id={id}")
    //@Operation(summary = "Getting all appointments from database")
    public ResponseEntity<?> listAppointmentsForClient(@PathVariable int id) {
        return new ResponseEntity<>(appointmentService.findAppointmentsForClient(id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAppointment(@PathVariable int id) {
        Optional<Appointment> appointment = appointmentService.findAppointmentById(id);
        if (appointment.isEmpty()) {
            throw new AppointmentNotFoundException(Constants.ID_NOT_FOUND);
        }
        return new ResponseEntity<>(appointment, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable int id){
        appointmentService.deleteAppointment(id);
        return ResponseEntity.ok(Constants.OBJECT_DELETED);
    }

    @PostMapping("/add")
    public ResponseEntity<AppointmentDto> addNewAppointment(@Valid @RequestBody AppointmentDto appointmentDto){
        return ResponseEntity.ok().body(appointmentService.saveNewAppointment(appointmentDto));
    }

    @PutMapping("/edit/date={date}/id={id}")
    public ResponseEntity<?> editAppointment(@PathVariable String date, @PathVariable int id) throws ParseException {
        return ResponseEntity.ok(appointmentService.editAppointment(id, date));
    }
}
