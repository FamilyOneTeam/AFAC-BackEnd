package com.AFAC_BackEnd.AFAC.DTO;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class MiembroFamiliaDTO {


    private Long id;


    private Long familiaId;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 255, message = "El nombre no puede exceder 255 caracteres")
    private String nombre;

    @NotBlank(message = "El RUT no puede estar vacío")
    @Size(max = 12, message = "El RUT no puede exceder 12 caracteres")
    private String rut;

    @NotNull(message = "La fecha de nacimiento no puede ser nula")
    private LocalDate fechaNacimiento;

    @Size(max = 50, message = "La relación no puede exceder 50 caracteres")
    private String relacion;

    // TODO: revisar y eliminar si no es necesario las validaciones
    @DecimalMin(value = "0.0", inclusive = true, message = "El ingreso debe ser mayor o igual a 0")
    @Digits(integer = 10, fraction = 2, message = "El ingreso debe ser un número con hasta 10 dígitos enteros y 2 decimales")
    private BigDecimal ingreso;
}

