package com.cristian.trabajoIntegrador.controllers;


import com.cristian.trabajoIntegrador.DTOs.AppointmentRequestDto;
import com.cristian.trabajoIntegrador.entities.Appointment;
import com.cristian.trabajoIntegrador.exceptions.ResourceNotFoundException;
import com.cristian.trabajoIntegrador.services.IAppointmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/turno")
public class AppointmentController {

    private IAppointmentService appointmentService;

    public AppointmentController(IAppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public ResponseEntity<Appointment> agregarTurno(@Valid @RequestBody AppointmentRequestDto turno) throws ResourceNotFoundException {

        Appointment turnoADevolver = appointmentService.saveAppointment(turno);
        return ResponseEntity.ok(turnoADevolver);

    }
    @GetMapping
    public ResponseEntity<List<Appointment>> buscarTodosTurnos(){
        List<Appointment> appointments = appointmentService.findAllAppointments();
        return ResponseEntity.ok(appointments);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@Valid @PathVariable Long id) throws ResourceNotFoundException {
        appointmentService.deleteAppointmentById(id);
        return ResponseEntity.ok("{\"message\": \"turno eliminado\"}");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> modificarTurno(@Valid @PathVariable Long id, @Valid  @RequestBody AppointmentRequestDto turno) throws ResourceNotFoundException {
        appointmentService.updateAppointment(id, turno);
        return ResponseEntity.ok("{\"message\": \"turno modificado\"}");
    }

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @GetMapping("/fechas")
    public ResponseEntity<List<Appointment>> buscarEntreFechas(@Valid @RequestParam String inicio, @Valid @RequestParam String fin){
        LocalDate fechaInicio = LocalDate.parse(inicio, formatter);
        LocalDate fechaFinal = LocalDate.parse(fin, formatter);

        return ResponseEntity.ok(appointmentService.findAppointmentsBetweenDates(fechaInicio, fechaFinal));
    }

}
