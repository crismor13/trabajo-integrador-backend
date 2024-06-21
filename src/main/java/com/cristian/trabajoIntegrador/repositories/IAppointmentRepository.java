package com.cristian.trabajoIntegrador.repositories;

import com.cristian.trabajoIntegrador.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface IAppointmentRepository extends JpaRepository<Appointment, Long> {
    //Buscar turnos entre dos fechas
    @Query("Select a from Appointment a where a.fecha BETWEEN :startDate and :endDate")
    List<Appointment> findBetweenDates(@Param("startDate")LocalDate startDate, @Param("endDate")LocalDate endDate);
}
