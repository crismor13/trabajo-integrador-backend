package com.cristian.trabajoIntegrador.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AppointmentRequestDto {
    @NotNull(message = "paciente_id is mandatory")
    private Long paciente_id;
    @NotNull(message = "odontologo_id is mandatory")
    private Long odontologo_id ;
    @NotNull(message = "fecha is mandatory")
    private String fecha;
    @NotNull(message = "hora is mandatory")
    private String hora;
}
