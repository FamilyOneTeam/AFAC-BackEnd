package com.AFAC_BackEnd.AFAC.DTO;

package cl.chile.somosafac.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class FichaInfanteDTO {

    @NotNull(message = "El ID no puede ser nulo")
    private Long id;

    @NotBlank(message = "El nombre del infante no puede estar vacío")
    @Size(max = 255, message = "El nombre no puede exceder 255 caracteres")
    private String nombreInfante;

    @NotBlank(message = "El RUT del infante no puede estar vacío")
    @Size(max = 12, message = "El RUT no puede exceder 12 caracteres")
    private String rutInfante;

    @NotNull(message = "La fecha de nacimiento no puede ser nula")
    private LocalDate fechaNacimientoInfante;

    private String region;

    private String comuna;

    private String direccion;

    private BigDecimal ingresoInfante;

    @Min(value = 0, message = "La edad no puede ser negativa")
    private Integer edadNna;

    private String rangoEdadNna;

    private String sexoNna;

    private String nacionalidadNna;

    private BigDecimal ingresoAfac;
}

