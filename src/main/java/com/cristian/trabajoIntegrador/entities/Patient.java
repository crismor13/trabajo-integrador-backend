package com.cristian.trabajoIntegrador.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name ="patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank(message = "Apellido is mandatory")
    private String apellido;
    @NotBlank(message = "Nombre is mandatory")
    private String nombre;
    @NotBlank(message = "Dni is mandatory")
    private String dni;
    @NotNull(message = "Fecha de ingreso is mandatory")
    private LocalDate fechaIngreso;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "domicilio_id")
    @NotNull(message = "Domicilio is mandatory")
    private Address domicilio;

    @OneToMany(mappedBy = "paciente",  cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Appointment> turnoSet = new HashSet<>();


}
