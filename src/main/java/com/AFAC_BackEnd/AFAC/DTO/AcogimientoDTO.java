package com.AFAC_BackEnd.AFAC.DTO;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AcogimientoDTO {

    @NotNull(message = "El ID no puede ser nulo")
    private Long id;

    @NotNull(message = "El ID de la familia no puede ser nulo")
    private Long familiaId;

    @NotNull(message = "El ID del infante no puede ser nulo")
    private Long infanteId;

    @NotNull(message = "La fecha de inicio no puede ser nula")
    @PastOrPresent(message = "La fecha de inicio no puede ser futura")
    private LocalDate fechaInicio;

    @FutureOrPresent(message = "La fecha de fin no puede ser pasada")
    private LocalDate fechaFin;

    @NotBlank(message = "El estado de acogimiento no puede estar vacío")
    @Size(max = 50, message = "El estado de acogimiento no puede exceder 50 caracteres")
    private String estadoAcogimiento;

    @Size(max = 500, message = "Las observaciones no pueden exceder 500 caracteres")
    private String observaciones;

    @NotNull(message = "El ID del usuario creador no puede ser nulo")
    private Long creadoPor;
}