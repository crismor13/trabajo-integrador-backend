package com.cristian.trabajoIntegrador.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    private String street;
    @NotBlank(message = "Número is mandatory")
    private int number;
    @NotBlank(message = "Localidad is mandatory")
    private String locality;
    @NotBlank(message = "Provincia is mandatory")
    private String province;
}
