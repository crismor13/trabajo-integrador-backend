package com.cristian.trabajoIntegrador.services;

import com.cristian.trabajoIntegrador.DTOs.AppointmentRequestDto;
import com.cristian.trabajoIntegrador.entities.Appointment;
import com.cristian.trabajoIntegrador.exceptions.ResourceNotFoundException;


import java.time.LocalDate;
import java.util.List;

public interface IAppointmentService {
    Appointment saveAppointment(AppointmentRequestDto newAppointment) throws ResourceNotFoundException;

    Appointment findAppointmentById(Long id) throws ResourceNotFoundException;

    List<Appointment> findAllAppointments();
    void updateAppointment(Long id, AppointmentRequestDto updatedAppointmentDto) throws ResourceNotFoundException;
    void deleteAppointmentById(Long id) throws ResourceNotFoundException;

    // HQL
    List<Appointment> findAppointmentsBetweenDates(LocalDate startDate,LocalDate endDate);
}
