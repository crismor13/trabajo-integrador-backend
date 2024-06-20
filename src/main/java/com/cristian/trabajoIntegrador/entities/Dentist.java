package com.cristian.trabajoIntegrador.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name ="dentists")
public class Dentist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Matrícula is mandatory")
    private String licenseNumber;
    @NotBlank(message = "Nombre is mandatory")
    private String name;
    @NotBlank(message = "Apellido is mandatory")
    private String lastName;

    @OneToMany(mappedBy = "dentist", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Appointment> appointmentsSet = new HashSet<>();
}
