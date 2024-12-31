package com.AFAC_BackEnd.AFAC.DTO;

import jakarta.validation.constraints.*;

public class FichaFamiliaDTO {

    private Long id;

    private Long usuarioId;

    private String estadoCivil;

    @Pattern(regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$", message = "El teléfono debe tener un formato válido")
    private String telefono;

    private String email;

    private String region;

    private String comuna;

    private String direccion;

    private String programaFundacionActual;

    private String programaFundacionAnterior;

    // TODO: revisar y eliminar si no es necesario las validaciones
    @DecimalMin(value = "0.0", inclusive = true, message = "El salario debe ser mayor o igual a 0")
    private Double salarioTotal;

    @Min(value = 0, message = "La cantidad de acogimientos no puede ser negativa")
    private Integer cantidadAcogimientos;

    private String estadoAcogimiento;
}
