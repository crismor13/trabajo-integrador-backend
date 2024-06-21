package com.cristian.trabajoIntegrador.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name ="appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    Patient paciente;
    @ManyToOne
    Dentist odontologo;

    @NotNull(message = "fecha is mandatory")
    LocalDate fecha;
    @NotNull(message = "hora is mandatory")
    LocalTime hora;
}
