package com.AFAC_BackEnd.AFAC.DTO;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RolDTO {

    @NotNull(message = "El ID del rol no puede ser nulo")
    private Long id;

    @NotBlank(message = "El nombre del rol no puede estar vacío")
    @Size(max = 50, message = "El nombre del rol no puede exceder 50 caracteres")
    private String nombre;

    @NotBlank(message = "La descripción del rol no puede estar vacía")
    @Size(max = 255, message = "La descripción del rol no puede exceder 255 caracteres")
    private String descripcion;
}

