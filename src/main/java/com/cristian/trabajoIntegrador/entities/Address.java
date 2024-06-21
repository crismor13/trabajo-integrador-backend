package com.cristian.trabajoIntegrador.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name ="addresses")

public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Calle is mandatory")
    private String calle;
    @NotNull(message = "Número is mandatory")
    private int numero;
    @NotNull(message = "Localidad is mandatory")
    private String localidad;
    @NotBlank(message = "Provincia is mandatory")
    private String provincia;
}
