package com.cristian.trabajoIntegrador.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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

    private String lastName;
    private String name;
    private String dni;
    private LocalDate startDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "domicilio_id")
    private Address address;

    @OneToMany(mappedBy = "patient",  cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Appointment> appointmentsSet = new HashSet<>();


}
