package com.AFAC_BackEnd.AFAC.DTO;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MentoriaDTO {

    @NotNull(message = "El ID no puede ser nulo")
    private Long id;

    @NotNull(message = "El ID de la familia mentora no puede ser nulo")
    private Long familiaMentoraId;

    @NotNull(message = "El ID de la familia mentorada no puede ser nulo")
    private Long familiaMentoradaId;

    @NotNull(message = "La fecha de asignación no puede ser nula")
    @PastOrPresent(message = "La fecha de asignación no puede ser futura")
    private LocalDateTime fechaAsignacion;

    @NotBlank(message = "El estado de mentoría no puede estar vacío")
    @Size(max = 50, message = "El estado de mentoría no puede exceder 50 caracteres")
    private String estadoMentoria;
}

